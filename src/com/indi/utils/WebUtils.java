package com.indi.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    /**
     * 适配所有需要注入JavaBean的类
     *
     * @param value 需要获取的参数
     * @param bean  存入到哪个类
     * @param <T>   任意类
     * @return 类对象
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成int类型的数据
     *
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
        if (strInt != null) {
            try {
                return Integer.parseInt(strInt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}