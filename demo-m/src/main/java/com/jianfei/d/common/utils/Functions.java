/**
  *project demo-m
  *@author changchun.wu
  *2017年6月27日下午5:08:21
  */
package com.jianfei.d.common.utils;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.util.CollectionUtils;

public class Functions {

    /**
     * 判断集合是否存在某个元素
     * @param iterable
     * @param element
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean in(Iterable iterable, Object element) {
        if(iterable == null || element == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }
    
    /**
     * 判断source集合是否存在candidates元素
     * @param source
     * @param candidates
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean containsAny(Collection source, Collection candidates) {
        return CollectionUtils.containsAny(source, candidates);
    }
    
    //判断url
    public static boolean equals(Iterable<String> menuUrls, String url) {
        if(menuUrls == null || url == null) {
            return false;
        }
        Iterator<String> iter = menuUrls.iterator();
        while(iter.hasNext()){
            String menuUrl = iter.next();
            if(url.equals(menuUrl)){
                return true;
            }
        }
        return false;
    }

}


