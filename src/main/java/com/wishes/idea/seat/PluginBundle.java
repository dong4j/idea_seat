package com.wishes.idea.seat;

import com.intellij.CommonBundle;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ResourceBundle;

/**
 * <p>Description: 国际化 <p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2021.03.09 13:12
 * @since 1.2.0
 */
public class PluginBundle {

    /** BUNDLE */
    @NonNls
    private static final String BUNDLE = "messages.PluginBundle";
    /** Our bundle */
    private static Reference<ResourceBundle> ourBundle;

    /**
     * Mik bundle
     *
     * @since 1.0.0
     */
    private PluginBundle() {
    }

    /**
     * Visibility presentation
     *
     * @param modifier modifier
     * @return the string
     * @since 1.0.0
     */
    @NotNull
    public static String visibilityPresentation(@NotNull String modifier) {
        return message(modifier + ".visibility.presentation");
    }

    /**
     * Message
     *
     * @param key    key
     * @param params params
     * @return the string
     * @since 1.0.0
     */
    @NotNull
    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, @NotNull Object... params) {
        return CommonBundle.message(getBundle(), key, params);
    }

    /**
     * Gets bundle *
     *
     * @return the bundle
     * @since 1.0.0
     */
    private static ResourceBundle getBundle() {
        ResourceBundle bundle = com.intellij.reference.SoftReference.dereference(ourBundle);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(BUNDLE);
            ourBundle = new SoftReference<>(bundle);
        }
        return bundle;
    }
}
