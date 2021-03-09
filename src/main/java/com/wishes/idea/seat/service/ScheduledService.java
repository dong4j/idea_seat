package com.wishes.idea.seat.service;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.wishes.idea.seat.service.impl.ScheduledServiceImpl;

import java.awt.event.ActionListener;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public interface ScheduledService {

    /**
     * 添加任务
     *
     * @param delay    延迟以及周期
     * @param listener 事件
     * @since 1.0.0
     */
    void addTask(int delay, ActionListener listener);

    /**
     * 延迟任务
     *
     * @param delay delay
     * @param flag  flag
     * @since 1.0.0
     */
    void addTask(int delay, boolean flag);

    /**
     * 是否有定时任务正在执行
     *
     * @return 是 /否
     * @since 1.0.0
     */
    boolean isTaskRunning();

    /**
     * 判断是否是空任务定时器
     *
     * @return 是 /否
     * @since 1.0.0
     */
    boolean isEmptyTaskTimer();

    /**
     * 移除任务
     *
     * @since 1.0.0
     */
    void removeTask();

    /**
     * 开始执行任务
     *
     * @since 1.0.0
     */
    void start();

    /**
     * 停止任务
     *
     * @since 1.0.0
     */
    void stop();

    /**
     * 延迟
     *
     * @param delay delay
     * @since 1.0.0
     */
    void delay(int delay);

    /**
     * getInstance
     *
     * @return {@link ScheduledServiceImpl}
     * @since 1.0.0
     */
    static ScheduledService getInstance() {
        if (ApplicationManager.getApplication() != null) {
            return ServiceManager.getService(ScheduledService.class);
        } else {
            return ScheduledServiceImpl.getInstance();
        }
    }
}
