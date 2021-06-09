package com.site.blog.my.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * {}@ClassName* Apply
 * {}@Description* 申请表
 * {}@Author* lht
 * {}@Date* 2021/6/8 23:47a
 **/
@Data
public class Apply {
    private Integer applyId;
    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Byte isDeleted;
    private String userName;
}
