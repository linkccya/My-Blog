package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.Apply;
import com.site.blog.my.core.entity.BlogComment;

import java.util.List;
import java.util.Map;

/**
 * {}@ClassName* ApplyMapper
 * {}@Description* 申请写博客
 * {}@Author* lht
 * {}@Date* 2021/6/9 0:06a
 **/
public interface ApplyMapper {
    int insert(Apply record);

    List<Apply> findApplyList(Map map);

    int getTotalApply(Map map);

    int checkDone(Integer[] ids);
}
