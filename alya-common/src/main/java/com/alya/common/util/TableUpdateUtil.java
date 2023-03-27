package com.alya.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alya.common.constant.DbConstants;

import java.lang.reflect.Field;

/**
 * @author gfye
 */
public class TableUpdateUtil {

    private TableUpdateUtil() {
    }

    /**
     * 对比更新
     *
     * @param clazz    更新的class
     * @param param 新值
     * @param oldValue 旧值
     * @param <T>      返回类型
     * @return T
     */
    public static <T> T get(Class<T> clazz, Object param, T oldValue) {
        boolean modified = false;
        T t = ReflectUtil.newInstance(clazz);
        Object id = ReflectUtil.getFieldValue(oldValue, DbConstants.ID);
        Field[] fields = ReflectUtil.getFields(clazz);
        for (Field field : fields) {
            Object newFieldValue = ReflectUtil.getFieldValue(param, field.getName());
            Object oldFieldValue = ReflectUtil.getFieldValue(oldValue, field.getName());
            if (ObjectUtil.isNotEmpty(newFieldValue) && ObjectUtil.notEqual(newFieldValue, oldFieldValue)) {
                ReflectUtil.setFieldValue(t, field.getName(), newFieldValue);
                modified = true;
            }
        }
        if (!modified) {
            t = null;
        } else {
            ReflectUtil.setFieldValue(t, DbConstants.ID, id);
        }
        return t;
    }

    public static boolean deleted(Integer isDeleted) {
        return 1 == isDeleted;
    }

    public static Integer updateDeleted(Integer isDeleted) {
        return 1 == isDeleted ? 0 : 1;
    }

}
