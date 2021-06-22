package org.sicnu.shop.controller;

import com.auth0.jwt.JWT;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.model.VOmodel.UserInfoVO;
import org.sicnu.shop.utilOss.AliyunOssHelper;
import org.sicnu.shop.utilRedis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    RedisUtil redisUtil;

    @Autowired
    AliyunOssHelper aliyunOssHelper;

    @GetMapping("/server/{id}")
    @ResponseBody
    public AjaxResponse server(@PathVariable("id") int id) {
        return AjaxResponse.success();
    }

    @PostMapping("/testRedis/{id}")
    @ResponseBody
    public AjaxResponse redistest(@PathVariable("id") int id) {
        UserInfoVO u  = new UserInfoVO();
        u.setEmail("546789");
        u.setNickName("AA");
        u.setRealName("BB");
        redisUtil.set("wr",u);
        System.out.println("已经存入redis");
        UserInfoVO uu = (UserInfoVO)redisUtil.get("wr");
        System.out.println(uu.getNickName() +  " "+uu.getRealName() +  " "+uu.getEmail());
        return AjaxResponse.success();

    }

    @PostMapping("/testUploadImage")
    @ResponseBody
    public AjaxResponse testUploadImage(@RequestParam(value = "imageFile",required = true) MultipartFile imageFile) {
        String imageUrl =aliyunOssHelper.uploadImage(imageFile);
//        String token = request.getHeader("token");
//        phone = JWT.decode(token).getAudience().get(0);
        return AjaxResponse.success(imageUrl);

    }
}
