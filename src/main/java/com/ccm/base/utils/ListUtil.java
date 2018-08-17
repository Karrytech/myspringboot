package com.ccm.base.utils;

import java.util.List;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/16 16:44
 * @Description:
 */
public class ListUtil {
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    public static boolean isSizeOne(List list) {
        return list != null && list.size() == 1;
    }
}
