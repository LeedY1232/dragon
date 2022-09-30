package com.dragon.server.util;

import java.util.Collection;

/**
 * @author henry
 * @date 2022/9/30 09:57
 */
public class CommonUtil {
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }
}
