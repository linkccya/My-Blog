package com.site.blog.my.core.controller.user;

import com.site.blog.my.core.entity.User;
import com.site.blog.my.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * {}@ClassName* UserController
 * {}@Description* 普通用户模块
 * {}@Author* lht
 * {}@Date* 2021/5/20 14:20a
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
    *跳到普通用户登录界面
    */
    @GetMapping("/login")
    public String login(){ return "user/login"; }

    /**
    *普通用户登录逻辑判断
    */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session){
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "user/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "user/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "user/login";
        }
        User user = userService.login(userName, password);
        if (user != null) {
            session.setAttribute("loginUser", user.getNickName());
            session.setAttribute("loginUserId", user.getUserId());
            return "redirect:/index";
        } else {
            session.setAttribute("errorMsg", "登陆失败");
            return "user/login";
        }
    }

    /**
    *跳到普通用户注册界面
    */
    @GetMapping("/register")
    public String register(){ return "user/register";}

    /**
     *普通用户登录逻辑判断
     */
    @PostMapping("/register")
    public String register(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("repassword") String repassword,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session){
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "user/register";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "user/register";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "user/register";
        }
        if(!password.equals(repassword)){
            session.setAttribute("errorMsg", "两次密码不同");
            return "user/register";
        }
        User user = userService.findByUserName(userName);
        if(user != null){
            session.setAttribute("errorMsg", "用户已存在");
            return "user/register";
        }else{
            try{
                int flag = userService.register(userName, password);
                if(flag == 1){return "user/login";}
                else{
                    session.setAttribute("errorMsg", "注册失败");
                    return "user/register";
                }
            }catch (Exception e){
                session.setAttribute("errorMsg", "注册失败");
                return "user/register";
            }
        }

    }
}
