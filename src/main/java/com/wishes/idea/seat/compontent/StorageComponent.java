package com.wishes.idea.seat.compontent;

import com.intellij.ide.util.PropertiesComponent;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class StorageComponent {
    /**
     * 存储
     *
     * @param key   key
     * @param value value
     * @since 1.0.0
     */
    public static void save(String key, String value) {
        PropertiesComponent.getInstance().setValue(key, value);
    }

    /**
     * 取值
     *
     * @param key key
     * @return 如果存在则返回值 ，不存在则返回null
     * @since 1.0.0
     */
    public static String getValue(String key) {
        return PropertiesComponent.getInstance().isValueSet(key)
               ? PropertiesComponent.getInstance().getValue(key)
               : null;
    }
}
