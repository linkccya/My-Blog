package com.site.blog.my.core.controller.user;

import com.site.blog.my.core.entity.History;
import com.site.blog.my.core.service.HistoryService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.Result;
import com.site.blog.my.core.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * {}@ClassName* HistoryController
 * {}@Description* 历史记录
 * {}@Author* lht
 * {}@Date* 2021/6/9 14:17a
 **/
@Controller
@RequestMapping("/user")
public class HistoryController {
    @Resource
    private HistoryService historyService;

    @GetMapping("/history")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "history");
        return "user/history";
    }

    @GetMapping("/history/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        int userId = (Integer) request.getSession().getAttribute("userLoginUserId");
        PageQueryUtil pageUtil = new PageQueryUtil(params,userId);
        return ResultGenerator.genSuccessResult(historyService.getHistoryPage(pageUtil));
    }
}
