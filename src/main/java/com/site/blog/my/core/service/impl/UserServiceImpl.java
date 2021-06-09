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
    public int register(String userName, String password, String email) {
        User user = new User();
        user.setLoginUserName(userName);
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        user.setLoginPassword(passwordMd5);
        user.setLocked((byte)0);
        String features = "0,0,0,0,0,0,0,0,0,0,0,0";
        user.setFeatures(features);
        String defaultNickName = UUID.randomUUID().toString().replaceAll("-","");
        user.setNickName(defaultNickName);
        user.setUserEmail(email);
        user.setRoleId(0);
        return userMapper.insert(user);
    }

    @Override
    public int updateInfo(int userId, String nickName,String userPhone,String userEmail) {
        return userMapper.updateInfo(userId,nickName,userPhone,userEmail);
    }

    @Override
    public Boolean updatePassword(String userName, String originalPassword, String newPassword) {
        User user = userMapper.findByUserName(userName);
        String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
        String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
        //比较原密码是否正确
        if (originalPasswordMd5.equals(user.getLoginPassword())) {
            //设置新密码并修改
            if (userMapper.updatePassword(user.getUserId(),newPasswordMd5) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByUserId(Integer Id) {
        return userMapper.findByUserId(Id);
    }

    @Override
    public Integer updateRoleByIds(Integer[] ids) {
        return userMapper.updateRoleByIds(ids);
    }

}
