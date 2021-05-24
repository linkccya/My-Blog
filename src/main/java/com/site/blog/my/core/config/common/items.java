package com.site.blog.my.core.config.common;

import com.site.blog.my.core.entity.Blog;
import lombok.Data;

/**
 * {}@ClassName* items
 * {}@Description* 博客带相似度值，用于排序与推荐
 * {}@Author* lht
 * {}@Date* 2021/5/23 19:39a
 **/
@Data
public class items {
    Blog blog;
    Integer value;
}
