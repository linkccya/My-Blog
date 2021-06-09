package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.Apply;
import com.site.blog.my.core.entity.History;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

/**
 * {}@ClassName* HistoryService
 * {}@Description* 历史记录
 * {}@Author* lht
 * {}@Date* 2021/6/9 14:22a
 **/
public interface HistoryService {
    /**
     *游览历史记录
     */
    int save(History history);


    /**
     * 游览记录分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getHistoryPage(PageQueryUtil pageUtil);
}
