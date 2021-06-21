//package org.sicnu.shop.utilOss;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.model.Bucket;
//import com.aliyun.oss.model.ObjectMetadata;
//import com.aliyun.oss.model.PutObjectResult;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//public class OSSUtil{
//
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;
//    @Value("${aliyun.oss.accessKeyId}")
//    private String accessKeyId;
//    @Value("${aliyun.oss.accessKeySecret}")
//    private String accessKeySecret;
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;
//
//    String imageUrl="";
//
//    public static String getUUID() {
//        String id = UUID.randomUUID().toString();
//        String uid = id.replaceAll("-", "");
//        return uid;
//    }
//
//    //文件存储目录
//    private String filedir = "images/";
//
//    private final Logger log = LoggerFactory.getLogger(OSSUtil.class);
//
//    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//    String fileName = getUUID() + ".jpg";
//
//    //设置header类型，这样图片才能在浏览器显示出来
//    ObjectMetadata omd = new ObjectMetadata();
//    omd.setContentType("image/jpg");
//    // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
//    ossClient.putObject(bucketName, fileName, file,omd);
//    // 关闭OSSClient。
//    ossClient.shutdown();
//    imageUrl = urlPrefix + fileName;
//    return imageUrl;
//
//}
