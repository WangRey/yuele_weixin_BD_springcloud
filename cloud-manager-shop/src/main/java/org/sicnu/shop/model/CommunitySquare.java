package org.sicnu.shop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "community_square")
public class CommunitySquare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int userId;

    @Column
    private String headImage;

    @Column
    private String nickName;

    @Column
    private String sharePicture;

    @Column
    private String shareText;

    @Column
    private String sharePlace;


}
