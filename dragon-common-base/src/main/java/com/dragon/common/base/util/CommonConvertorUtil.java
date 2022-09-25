package com.dragon.common.base.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Li Dongyang
 * @date 2022/9/25 20:41
 */
public class CommonConvertorUtil {
    public static  <V,E> List<V> convertToListViews(List<E> items, Function<E,V> function){
        if(items!=null&&!items.isEmpty()){
            return items.stream().filter(Objects::nonNull)
                    .map(function)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
