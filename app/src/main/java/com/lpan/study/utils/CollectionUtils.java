package com.lpan.study.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by lpan on 2016/8/10.
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection){
        return ((collection == null) || collection.isEmpty());
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return ((map == null) || map.isEmpty());
    }
}
