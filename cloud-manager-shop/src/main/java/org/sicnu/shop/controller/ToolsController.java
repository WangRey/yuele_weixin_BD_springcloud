package org.sicnu.shop.controller;

import com.auth0.jwt.JWT;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.utilOss.AliyunOssHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ToolsController {

    @Autowired
    AliyunOssHelper aliyunOssHelper;

    @PostMapping("/uploadImage")
    @ResponseBody
    public AjaxResponse testUploadImage(@RequestParam(value = "imageFile",required = true ) MultipartFile imageFile , HttpServletRequest request) {
        String imageUrl =aliyunOssHelper.uploadImage(imageFile);
        String token = request.getHeader("token");
        String  phone = JWT.decode(token).getAudience().get(0);
        return AjaxResponse.success(imageUrl);
    }
}
