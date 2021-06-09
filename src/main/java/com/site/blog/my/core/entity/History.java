package com.site.blog.my.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * {}@ClassName* History
 * {}@Description* 历史记录
 * {}@Author* lht
 * {}@Date* 2021/6/9 14:24a
 **/
@Data
public class History {
    private Long historyId;
    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Long blogId;
    private String blogTitle;
    private Integer categoryId;
}
