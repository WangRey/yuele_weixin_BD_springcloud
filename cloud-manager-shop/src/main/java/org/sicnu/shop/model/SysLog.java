package org.sicnu.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "sys_log")
public class SysLog {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userId;
    @Column
    private String requestUrl;
    @Column
    private String authorities;
    @Column
    private String ip;
    @Column
    private String locale;
    @Column
    private String resultMessage;
    @Column
    private String data;
    @Column
    private String dataExplain;
    @Column
    private String type;
    @Column
    private String typeExplain;
    @Column
//    @Temporal(TemporalType.TIME)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column
    private String params;

}
