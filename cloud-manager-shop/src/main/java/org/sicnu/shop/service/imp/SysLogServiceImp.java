package org.sicnu.shop.service.imp;

import com.auth0.jwt.JWT;
import org.codehaus.jettison.json.JSONObject;
import org.sicnu.shop.dao.SysLogRepository;
import org.sicnu.shop.dao.UserRepository;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.model.SysLog;
import org.sicnu.shop.model.enums.SysLogType;
import org.sicnu.shop.service.SysLogService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Service
public class SysLogServiceImp implements SysLogService {

    @Resource
    SysLogRepository sysLogRepository;

    @Resource
    UserRepository userRepository;


    /**
     * name:type
     * value:count
     * @return
     */
    @Override
    public ArrayList<Map<String, Integer>> getLogTypeAndCount() {
        return sysLogRepository.getLogTypeAndCount();
    }

    /**
     * create_time
     * total
     * @return
     */
    @Override
    public ArrayList<Map<String, Integer>> getLogInfo() {
        return sysLogRepository.getLogInfo();
    }

    @Override
    public void addLog(HttpServletRequest request, AjaxResponse aj) {
        SysLog sysLog = new SysLog();

        String token = request.getHeader("token");
        int userId = 9999;
        if(token != null){
            String phone  = JWT.decode(token).getAudience().get(0);
            userId= userRepository.findUserByPhone(phone).getId();
        }

        sysLog.setUserId(String.valueOf(userId));
        sysLog.setRequestUrl(request.getRequestURL().toString());
        sysLog.setLocale(request.getLocale().toString());
        sysLog.setIp(request.getRemoteAddr());

        if (sysLog.getRequestUrl().endsWith("login") && request.getMethod().equals("POST")) {
            sysLog.setType(SysLogType.LoginLog.getType());
            sysLog.setTypeExplain(SysLogType.LoginLog.getTypeExplain());
        } else {
            sysLog.setType(SysLogType.OperationLog.getType());
            sysLog.setTypeExplain(SysLogType.OperationLog.getTypeExplain());
        }

        Date nowdate=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sysLog.setCreateTime(Timestamp.valueOf(simpleDate.format(nowdate)));
        sysLog.setResultMessage(aj.getMessage());
        sysLog.setData(String.valueOf(aj.getCode()));
        sysLog.setDataExplain(aj.getMessage());
        sysLog.setParams(this.requestGetJson(request));

        sysLogRepository.save(sysLog);

    }

    /**
     * 百度的获取request里面的json数据
     * @param request
     * @return
     */
    public String requestGetJson(HttpServletRequest request) {
        //自定义从bodyt中获取json格式数据
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) { /*report an error*/ }
        //将空格和换行符替换掉避免使用反序列化工具解析对象时失败
        System.out.println(sb);
        String jsonString = sb.toString().replaceAll("\\s","").replaceAll("\n","");
        System.out.println(jsonString);
        return jsonString;
    }
}
