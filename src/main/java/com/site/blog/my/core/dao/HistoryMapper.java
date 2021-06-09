package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.Apply;
import com.site.blog.my.core.entity.History;

import java.util.List;
import java.util.Map;

/**
 * {}@ClassName* HistoryMapper
 * {}@Description* 历史记录
 * {}@Author* lht
 * {}@Date* 2021/6/9 14:46a
 **/
public interface HistoryMapper {
    int insert(History record);

    List<History> findHistoryList(Map map);

    int getTotalHistory(Map map);

    /**
    *根据用户id获取对应记录
    */
    List<History> findHistoryByUserId(Integer userId);
}
