package com.site.blog.my.core.controller.admin;

import com.site.blog.my.core.dao.UserMapper;
import com.site.blog.my.core.service.BlogerService;
import com.site.blog.my.core.service.UserService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.Result;
import com.site.blog.my.core.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * {}@ClassName* ApplyController
 * {}@Description* 管理员审核
 * {}@Author* lht
 * {}@Date* 2021/6/9 0:42a
 **/
@Controller
@RequestMapping("/admin")
public class ApplyController {
    @Resource
    private BlogerService blogerService;
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @GetMapping("/apply/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(blogerService.getApplyPage(pageUtil));
    }

    @GetMapping("/apply")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "apply");
        return "admin/apply";
    }

    @PostMapping("/apply/checkDone")
    @ResponseBody
    public Result checkDone(HttpServletRequest request,@RequestBody Integer[] ids) {
        System.out.println("正常输出");
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (blogerService.checkDone(ids)) {
            int len = ids.length;
            for(int i=0;i<len;i++){
                userMapper.updateRole(ids[i],1);
            }
            if(request.getSession().getAttribute("userRoleId")!=null){ request.getSession().setAttribute("userRoleId",1);}
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("审核失败");
        }
    }

    @PostMapping("/apply/delete")
    @ResponseBody
    public Result delete(HttpServletRequest request,@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (blogerService.checkDone(ids)) {
            int len = ids.length;
            for(int i=0;i<len;i++){
                userMapper.updateRole(ids[i],0);
            }
            if(request.getSession().getAttribute("userRoleId")!=null){ request.getSession().setAttribute("userRoleId",0);}
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
