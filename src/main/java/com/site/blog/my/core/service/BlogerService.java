package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.Apply;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

/**
 * {}@ClassName* BlogerService
 * {}@Description* 申请博客
 * {}@Author* lht
 * {}@Date* 2021/6/8 23:42a
 **/
public interface BlogerService {
    /**
    *博客申请
    */
    int save(Apply apply);


    /**
     * 后台管理系统中申请分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getApplyPage(PageQueryUtil pageUtil);

    /**
     * 批量审核
     *
     * @param ids
     * @return
     */
    Boolean checkDone(Integer[] ids);

}
