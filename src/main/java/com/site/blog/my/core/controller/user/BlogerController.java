package com.site.blog.my.core.controller.user;

import com.site.blog.my.core.dao.UserMapper;
import com.site.blog.my.core.entity.Apply;
import com.site.blog.my.core.service.BlogerService;
import com.site.blog.my.core.service.CategoryService;
import com.site.blog.my.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * {}@ClassName* BlogerController
 * {}@Description* 申请写博客权限
 * {}@Author* lht
 * {}@Date* 2021/6/8 23:38a
 **/
@Controller
@RequestMapping("/user")
public class BlogerController {
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private BlogerService blogerService;
    @Resource
    private UserMapper userMapper;

    /**
     *跳到写博客页面
     */
    @GetMapping("/write")
    public String write(HttpServletRequest request){
        request.setAttribute("userPath", "write");
        request.setAttribute("categories", categoryService.getAllCategories());
        return "user/write";
    }

    /**
     *申请写博客
     */
    @GetMapping("/bloger/{userId}")
    public String bloger(HttpServletRequest request, @PathVariable("userId") Integer userId){
        Apply apply = new Apply();
        apply.setUserId(userId);
        apply.setIsDeleted((byte)0);
        apply.setCreateTime(new Date());
        apply.setUserName(userMapper.findByUserId(userId).getLoginUserName());
        blogerService.save(apply);
        userMapper.updateRole(userId,2);
        request.getSession().setAttribute("userRoleId",2);
        return "redirect:/user/write";
    }
}
