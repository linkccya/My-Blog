package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * {}@ClassName* UserMapper
 * {}@Description* 用户模块数据库操作
 * {}@Author* lht
 * {}@Date* 2021/5/20 17:12a
 **/
public interface UserMapper {
    /**
    *登录
    */
    User login(@Param("userName") String userName, @Param("password") String password);

    /**
    *根据用户名查询普通用户
    */
    User findByUserName(String userName);

    /**
    *插入用户
    */
    int insert(User user);

    /**
    *修改特征
    */
    int update(@Param("UserId")int UserId,@Param("features")String features);
}
