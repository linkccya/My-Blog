package com.site.blog.my.core.controller.user;

import com.site.blog.my.core.entity.User;
import com.site.blog.my.core.service.CategoryService;
import com.site.blog.my.core.service.UserService;
import com.site.blog.my.core.util.Result;
import com.site.blog.my.core.util.ResultGenerator;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private CategoryService categoryService;

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
            session.setAttribute("userErrorMsg", "验证码不能为空");
            return "user/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("userErrorMsg", "用户名或密码不能为空");
            return "user/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("userErrorMsg", "验证码错误");
            return "user/login";
        }
        User user = userService.login(userName, password);
        if (user != null) {
            session.setAttribute("userLoginNickname", user.getNickName());
            session.setAttribute("userLoginUserId", user.getUserId());
            session.setAttribute("userLoginName",user.getLoginUserName());
            session.setAttribute("userEmail",user.getUserEmail());
            session.setAttribute("userPhone",user.getUserPhone());
            session.setAttribute("userRoleId",user.getRoleId());
            return "redirect:/index";
        } else {
            session.setAttribute("userErrorMsg", "登陆失败");
            return "user/login";
        }
    }

    /**
    *跳到普通用户注册界面
    */
    @GetMapping("/register")
    public String register(){ return "user/register";}

    /**
     *普通用户注册逻辑判断
     */
    @PostMapping("/register")
    public String register(@RequestParam("userName") String userName,
                           @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("repassword") String repassword,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session){
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("userRegisterErrorMsg", "验证码不能为空");
            return "user/register";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("userRegisterErrorMsg", "用户名或密码不能为空");
            return "user/register";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("userRegisterErrorMsg", "验证码错误");
            return "user/register";
        }
        if(!password.equals(repassword)){
            session.setAttribute("userRegisterErrorMsg", "两次密码不同");
            return "user/register";
        }
        User user = userService.findByUserName(userName);
        if(user != null){
            session.setAttribute("userRegisterErrorMsg", "用户已存在");
            return "user/register";
        }else{
            try{
                int flag = userService.register(userName, password,email);
                if(flag == 1){return "user/login";}
                else{
                    session.setAttribute("userRegisterErrorMsg", "注册失败");
                    return "user/register";
                }
            }catch (Exception e){
                session.setAttribute("userRegisterErrorMsg", "注册失败");
                return "user/register";
            }
        }

    }

    /**
    *普通用户后台
    */
    @GetMapping("/index")
    public String profile(HttpServletRequest request){
        request.setAttribute("userPath", "index");
        return "user/index";
    }

    /**
    *更新用户信息
    */
    @PostMapping("/userInfo")
    @ResponseBody
    public String userInfo(@RequestParam(value = "userId", required = true) int userId,
                           @RequestParam(value = "nickName", required = true) String nickName,
                           @RequestParam(value = "userPhone", required = false) String userPhone,
                           @RequestParam(value = "userEmail", required = true) String userEmail,
                           HttpSession session){
        int updateResult = 0;
        updateResult = userService.updateInfo(userId,nickName, userPhone, userEmail);
        if(updateResult>0){
            session.setAttribute("userLoginNickname", nickName);
            session.setAttribute("userEmail", userEmail);
            session.setAttribute("userPhone", userPhone);
            return "success";
        }
        return "修改失败";
    }

    /**
     *更新用户密码
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestParam(value = "userId2", required = true) String userName,
                           @RequestParam(value = "originalPassword", required = true) String originalPassword,
                           @RequestParam(value = "newPassword", required = true) String newPassword,
                                 HttpServletRequest request){
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        if (userService.updatePassword(userName, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("userLoginUserId");
            request.getSession().removeAttribute("userLoginNickname");
            request.getSession().removeAttribute("userErrorMsg");
            request.getSession().removeAttribute("userLoginName");
            request.getSession().removeAttribute("userEmail");
            request.getSession().removeAttribute("userPhone");
            request.getSession().removeAttribute("userRoleId");
            return "success";
        } else {
            return "修改失败";
        }
    }


    /**
    *退出
    */
    @GetMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("userLoginUserId");
        request.getSession().removeAttribute("userLoginNickname");
        request.getSession().removeAttribute("userErrorMsg");
        request.getSession().removeAttribute("userLoginName");
        request.getSession().removeAttribute("userEmail");
        request.getSession().removeAttribute("userPhone");
        request.getSession().removeAttribute("userRoleId");
        return "redirect:/index";
    }
}
