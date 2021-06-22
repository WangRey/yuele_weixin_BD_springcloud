package org.sicnu.shop.utilOss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class AliyunOssHelper {

    //------------------变量----------
    protected static final Logger log = LoggerFactory.getLogger(AliyunOssHelper.class);

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;


    public static String getUUID(){

        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");
        return uid;
    }
    public  String uploadImage(MultipartFile mFile){

        System.out.println(this.toString());

        String imageUrl="";
        String urlPrefix = "https://"+bucketName+"."+endpoint+"/";

        OSS ossClient = new OSSClientBuilder().build(this.endpoint, this.accessKeyId, this.accessKeySecret);

        File file= new File("F:\\AI\\temp.jpg");

        try {
            mFile.transferTo(file);
        }catch (Exception e){
            System.out.println(e);
        }

        String fileName = getUUID()+".jpg";
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, fileName, file);
        //设置header类型，这样图片才能在图书馆显示
        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentType("image/jpg");
        // 关闭OSSClient。
        ossClient.shutdown();
        imageUrl = urlPrefix+fileName;
        return imageUrl;
    }

    public static Logger getLog() {
        return log;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }


    @Override
    public String toString() {
        return "AliyunOssHelper{" +
                "endpoint='" + endpoint + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", bucketName='" + bucketName + '\'' +
                '}';
    }
}