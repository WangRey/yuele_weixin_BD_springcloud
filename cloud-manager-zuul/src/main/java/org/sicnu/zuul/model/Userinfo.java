package org.sicnu.zuul.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Userinfo {
    public static enum Sex {
        male,
        female;
    }
    private Integer id;

    private String email;


    private String nickName ;

    private String phone;

    private String realName ;

    private String password;

    private Sex sex = null;

    private String idCard;

    private String balance;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBirthday() {
        return birthday;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
