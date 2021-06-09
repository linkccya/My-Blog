package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.ApplyMapper;
import com.site.blog.my.core.dao.HistoryMapper;
import com.site.blog.my.core.entity.Apply;
import com.site.blog.my.core.entity.History;
import com.site.blog.my.core.service.HistoryService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {}@ClassName* HistoryServiceImpl
 * {}@Description* TODO
 * {}@Author* lht
 * {}@Date* 2021/6/9 14:23a
 **/
@Service
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;

    @Override
    public int save(History history) {
        return historyMapper.insert(history);
    }

    @Override
    public PageResult getHistoryPage(PageQueryUtil pageUtil) {
        List<History> historyList = historyMapper.findHistoryList(pageUtil);
        int total = historyMapper.getTotalHistory(pageUtil);
        PageResult pageResult = new PageResult(historyList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
