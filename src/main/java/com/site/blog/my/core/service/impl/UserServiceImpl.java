package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.UserMapper;
import com.site.blog.my.core.entity.User;
import com.site.blog.my.core.service.UserService;
import com.site.blog.my.core.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * {}@ClassName* UserServiceImpl
 * {}@Description* 用户操作实现
 * {}@Author* lht
 * {}@Date* 2021/5/20 16:39a
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return userMapper.login(userName, passwordMd5);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public int register(String userName, String password) {
        User user = new User();
        user.setLoginUserName(userName);
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        user.setLoginPassword(passwordMd5);
        user.setLocked((byte)0);
        String features = "0,0,0,0,0,0,0,0,0,0,0,0";
        user.setFeatures(features);
        String defaultNickName = UUID.randomUUID().toString().replaceAll("-","");
        user.setNickName(defaultNickName);
        return userMapper.insert(user);
    }
}
