package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.ApplyMapper;
import com.site.blog.my.core.dao.UserMapper;
import com.site.blog.my.core.entity.Apply;
import com.site.blog.my.core.entity.BlogComment;
import com.site.blog.my.core.service.BlogerService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {}@ClassName* BlogerServiceImpl
 * {}@Description* 申请博客
 * {}@Author* lht
 * {}@Date* 2021/6/8 23:43a
 **/
@Service
public class BlogerServiceImpl implements BlogerService {
    @Resource
    private ApplyMapper applyMapper;

    @Override
    public int save(Apply apply) {
       return applyMapper.insert(apply);
    }

    @Override
    public PageResult getApplyPage(PageQueryUtil pageUtil) {
        List<Apply> applyList = applyMapper.findApplyList(pageUtil);
        int total = applyMapper.getTotalApply(pageUtil);
        PageResult pageResult = new PageResult(applyList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean checkDone(Integer[] ids) {
        return applyMapper.checkDone(ids) > 0;
    }

}
