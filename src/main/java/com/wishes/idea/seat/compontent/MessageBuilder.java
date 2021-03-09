package com.wishes.idea.seat.compontent;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Objects;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class MessageBuilder {
    /**
     * 自己的一个返回消息主体
     */
    private String body;

    /**
     * 存放参数变量的数组
     */
    private Object[] params;

    /**
     * 构造方法
     *
     * @since 1.0.0
     */
    public MessageBuilder() {
        this.body = "这是一个MessageBuilder模板，使用占位符表示:变量1({0}),变量2({1})";
    }

    /**
     * 设置消息主体
     *
     * @param body 消息主体
     * @return MessageBuilder.this body
     * @since 1.0.0
     */
    public MessageBuilder setBody(String body) {
        if (StringUtils.isBlank(body)) {
            throw new IllegalArgumentException("消息主体不能为空！");
        } else {
            this.body = body;
        }

        return this;
    }

    /**
     * 设置变量
     *
     * @param params 变量参数们
     * @return MessageBuilder.this variables
     * @since 1.0.0
     */
    public MessageBuilder setVariables(Object... params) {
        Objects.requireNonNull(params);
        for (Object o : params) {
            Objects.requireNonNull(o, "变量参数不能为空！");
        }

        this.params = params;
        return this;
    }

    /**
     * build出String字符串的方法
     *
     * @return String 完整提示消息
     * @see #toString() #toString()
     * @since 1.0.0
     */
    public final String build() {
        return this.toString();
    }

    /**
     * build出String字符串的方法
     *
     * @return String 完整提示消息
     * @since 1.0.0
     */
    @Override
    public String toString() {
        return MessageFormat.format(this.body, this.params);
    }
}
