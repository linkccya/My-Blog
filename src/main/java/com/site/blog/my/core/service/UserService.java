package com.site.blog.my.core.service;


import com.site.blog.my.core.entity.User;

/**
 * {}@ClassName* UserService
 * {}@Description* 用户模块逻辑处理
 * {}@Author* lht
 * {}@Date* 2021/5/20 16:37a
 **/
public interface UserService {
    /**
    *登录
    */
    User login(String userName, String password);

    /**
    *根据用户名查询用户
    */
    User findByUserName(String userName);

    /**
    *注册用户
    */
    int register(String userName, String password);
}
