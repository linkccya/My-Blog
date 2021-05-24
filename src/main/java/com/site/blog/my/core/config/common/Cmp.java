package com.site.blog.my.core.config.common;

import java.util.Comparator;

/**
 * {}@ClassName* Cmp
 * {}@Description* 用于条件排序
 * {}@Author* lht
 * {}@Date* 2021/5/23 19:40a
 **/
public class Cmp implements Comparator<items> {

    public int compare(items A, items B) ///降序排序
    {
        if(A.value<B.value)
        {
            return 1;
        }
        else if(A.value==B.value)
        {
            return 0;
        }
        else
        {
            return -1;
        }

    }
}
