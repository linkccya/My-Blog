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
     *根据ID查询普通用户
     */
    User findByUserId(Integer Id);

    /**
    *插入用户
    */
    int insert(User user);

    /**
    *修改特征
    */
    int update(@Param("UserId")int UserId, @Param("features")String features);

    /**
    *修改个人信息
    */
    int updateInfo(@Param("userId")int userId, @Param("nickName")String nickName,
    @Param("userPhone")String userPhone, @Param("userEmail")String userEmail);

    /**
    *修改密码
    */
    int updatePassword(@Param("userId")int userId, @Param("password")String password);

    /**
    *修改角色
    */
    int updateRole(@Param("userId")int userId, @Param("roleId")int roleId);

    int updateRoleByIds(Integer[] ids);

}
