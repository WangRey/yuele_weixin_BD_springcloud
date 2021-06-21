package org.sicnu.root;

import org.sicnu.root.repo.GoodsRepo;
import org.sicnu.root.repo.GoodstoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableFeignClients
public class RootApplication {
    @Autowired
    private GoodsRepo goodsRepo;
    @Autowired
    private GoodstoreRepo goodstoreRepo;

    public static void main(String[] args){
        SpringApplication.run(RootApplication.class,args);
    }
}
