package com.site.blog.my.core.entity;

import lombok.Data;

/**
 * {}@ClassName* User
 * {}@Description* 普通用户信息存储
 * {}@Author* lht
 * {}@Date* 2021/5/20 16:30a
 **/
@Data
public class User {
    /**
    *用户ID
    */
    private Integer UserId;

    private String loginUserName;

    private String loginPassword;

    private String nickName;

    private String userEmail;

    private String userPhone;

    /**
    *角色类型
    */
    private Integer roleId;

    /**
    *向量
    */
    private String features;

    private Byte locked;
}
