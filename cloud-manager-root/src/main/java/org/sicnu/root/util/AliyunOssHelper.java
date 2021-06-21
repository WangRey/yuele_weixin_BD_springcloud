package org.sicnu.root.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

public class AliyunOssHelper {
    public static String getUUID(){

        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");
        return uid;
    }
    public static String uploadImage(MultipartFile mFile){
        String imageUrl="";
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tCX9McJgReXDnG9Jtfq";
        String accessKeySecret = "CeRUYiRCdo7hURALziqwQBO13ts6Lo";
        String urlPrefix = "https://dijgorylaiying.oss-cn-hangzhou.aliyuncs.com/";
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        File file= new File("d:\\试一试（桌面上的视频）\\大三下学期课程资料\\千峰实习\\2021千峰H5实习\\文件\\代码需要文件\\temp.jpg");

        try {
            mFile.transferTo(file);
        }catch (Exception e){
            System.out.println(e);
        }

        String fileName = getUUID()+".jpg";
// 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("dijgorylaiying", fileName, file);
        //设置header类型，这样图片才能在图书馆显示
        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentType("image/jpg");
// 关闭OSSClient。
        ossClient.shutdown();
        imageUrl = urlPrefix+fileName;
        return imageUrl;
    }
}