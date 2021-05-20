package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.UserMapper;
import com.site.blog.my.core.entity.User;
import com.site.blog.my.core.service.UserService;
import com.site.blog.my.core.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {}@ClassName* UserServiceImpl
 * {}@Description* 用户操作实现
 * {}@Author* lht
 * {}@Date* 2021/5/20 16:39a
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper UserMapper;

    @Override
    public User login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return UserMapper.login(userName, passwordMd5);
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public int register(String userName, String password) {
        return 1;
    }
}
