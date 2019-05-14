package com.ruijie.sncheck.common.util;

import org.apache.commons.lang.StringEscapeUtils;

import java.lang.reflect.Field;

/**
 * ObjectUtil
 *
 * @author {yuanwei}
 * @date 2019/3/27 14:25
 */
public class ObjectUtil {
    public static <T> void mergeObject(T origin, T destination) {
        if (origin == null || destination == null)
            return;
        if (!origin.getClass().equals(destination.getClass()))
            return;

        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(origin);
                if (null != value) {
                    fields[i].set(destination, value);
                }
                fields[i].setAccessible(false);
            } catch (Exception e) {
            }
        }
    }

    public static <T> T setNullValue(T source) throws IllegalArgumentException, IllegalAccessException, SecurityException {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getGenericType().toString().equals(
                    "class java.lang.String")) {
                field.setAccessible(true);
                Object obj = field.get(source);
                if (obj != null && obj.equals("")) {
                    field.set(source, null);
                } else if (obj != null) {
                    String str = obj.toString();
                    str = StringEscapeUtils.escapeSql(str);//StringEscapeUtils是commons-lang中的通用类
                    field.set(source, str.replace("\\", "\\" + "\\").replace("(", "\\(").replace(")", "\\)")
                            .replace("%", "\\%").replace("*", "\\*").replace("[", "\\[").replace("]", "\\]")
                            .replace("|", "\\|").replace(".", "\\.").replace("$", "\\$").replace("+", "\\+").trim()
                    );
                }
            }
        }
        return source;
    }
}
