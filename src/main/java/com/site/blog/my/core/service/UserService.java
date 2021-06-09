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
    int register(String userName, String password, String email);

    /**
    *更新个人信息
    */
    int updateInfo(int userId, String nickName,String userPhone,String userEmail);

    /**
    *修改密码
    */
    Boolean updatePassword(String userName, String originalPassword, String newPassword);

    /**
     *根据ID查询用户
     */
    User findByUserId(Integer Id);

    /**
     * 批量修改权限
     *
     * @param ids
     * @return
     */
    Integer updateRoleByIds(Integer[] ids);
}
