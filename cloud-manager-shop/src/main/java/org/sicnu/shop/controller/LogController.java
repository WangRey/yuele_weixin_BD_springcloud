package org.sicnu.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.model.enums.SysLogType;
import org.sicnu.shop.service.imp.SysLogServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    SysLogServiceImp sysLogServiceImp;

    /**
     * 获得所有日志类型已经相应的数量
     *
     * @return 结果集
     */
    @GetMapping("/getLogTypeAndCount")
    public AjaxResponse getLogTypeAndCount() {
        return AjaxResponse.success(sysLogServiceImp.getLogTypeAndCount());
    }

    /**
     * 获得日志的相关信息
     * @return  结果集
     */
    @GetMapping("/getLogInfo")
    public AjaxResponse getLogInfo() {
        return AjaxResponse.success(sysLogServiceImp.getLogInfo());
    }


}
