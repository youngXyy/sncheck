package com.ruijie.sncheck.common.util;

import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * CopyBean
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:56
 */
public class CopyBean {
    public static  <U> U simpleCopy(Object object, Class<U> targetClass, String... ignoreProperty) {
        return Optional.of(object).map(v ->{
            U instance = null;
            try {
                instance = targetClass.newInstance();
                BeanUtils.copyProperties(v,instance,ignoreProperty);
            } catch ( IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

            return instance;
        }).get();
    }

    public static  <U> U simpleCopy(Object object, Class<U> targetClass) {
        return Optional.of(object).map(v ->{
            U instance = null;
            try {
                instance = targetClass.newInstance();
                BeanUtils.copyProperties(v,instance);
            } catch ( IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

            return instance;
        }).get();
    }
}
