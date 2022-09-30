package com.dragon.server.util;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.dragon.server.exception.ParameterException;

/**
 * @author Li Dongyang
 * @date 2022/9/25 18:35
 */
public class ParamCheckUtil {
    public static <T> T assertParameterNotNull(T object, String message) {
        if (object == null) {
            throw new ParameterException(message);
        }
        return object;
    }

    public static String assertParameterNotBlank(String s, String message) {
        if (StringUtils.isBlank(StringUtils.trimToNull(s))) {
            throw new ParameterException(message);
        }
        return StringUtils.trimToNull(s);
    }

    public static <E> Collection<E> assertCollectionNotEmpty(Collection<E> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new ParameterException(message);
        }
        return collection;
    }

    public static void assertArgument(boolean argument, String message) {
        if (!argument) {
            throw new ParameterException(message);
        }
    }
}
