package org.sicnu.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "userinfo")
public class Userinfo {
    public static enum Sex {
        male,
        female;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128,nullable = false)
    private String email;


    @Column(length = 128, nullable = false)
    private String nickName ;

    @Column(nullable = false)
    private String phone;

    @Column(length = 128, nullable = false)
    private String realName ;

    @Column(length = 255, nullable = false)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex = null;

    @Column(length = 128,nullable = false)
    private String idCard;

    @Column
    private String balance;
    @Column
    private String headImage;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBirthday() {
        return birthday;
    }

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
