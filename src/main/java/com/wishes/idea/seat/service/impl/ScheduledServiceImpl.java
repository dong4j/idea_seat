package com.wishes.idea.seat.service.impl;

import com.wishes.idea.seat.service.ScheduledService;

import java.awt.event.ActionListener;

import javax.swing.Timer;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class ScheduledServiceImpl implements ScheduledService {
    /**
     * 唯一timer
     */
    private Timer timer;

    /** Delay */
    private int delay;

    /** listener */
    public static ActionListener listener;

    /**
     * Gets timer *
     *
     * @return the timer
     * @since 1.0.0
     */
    private Timer getTimer() {
        return this.timer;
    }

    /**
     * Sets timer *
     *
     * @param timer timer
     * @since 1.0.0
     */
    private void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Scheduled service
     *
     * @since 1.0.0
     */
    private ScheduledServiceImpl() {
        this.timer = this.createEmptyTask();
    }

    /**
     * <p>Description: </p>
     *
     * @author wishes
     * @version 1.0.0
     * @email "mailto:1098832322@qq.com"
     * @date 2021.03.09 13:52
     * @since 1.0.0
     */
    private static class ScheduledServiceHolder {
        /** INSTANCE */
        private static final ScheduledServiceImpl INSTANCE = new ScheduledServiceImpl();
    }

    /**
     * instance
     *
     * @return {@link ScheduledServiceImpl}
     * @since 1.0.0
     */
    public static ScheduledServiceImpl getInstance() {
        return ScheduledServiceHolder.INSTANCE;
    }

    /**
     * 创建一个空任务
     *
     * @return empty task timer
     * @since 1.0.0
     */
    private Timer createEmptyTask() {
        return new Timer(0, null);
    }

    /**
     * 添加任务
     *
     * @param delay    延迟以及周期
     * @param listener 事件
     * @since 1.0.0
     */
    @Override
    public void addTask(int delay, ActionListener listener) {
        ScheduledServiceImpl.listener = listener;
        this.delay = delay;
        synchronized (getInstance().getTimer()) {
            if (this.isTaskRunning()) {
                this.removeTask();
            }
            getInstance().setTimer(new Timer(delay, listener));
        }
    }

    /**
     * 延迟任务
     *
     * @param delayTime delay time
     * @param flag      flag
     * @since 1.0.0
     */
    @Override
    public void addTask(int delayTime, boolean flag) {
        if (flag) {
            getInstance().setTimer(new Timer(delayTime + this.delay, listener));
        } else {
            getInstance().setTimer(new Timer(delayTime, listener));
        }
        this.start();
    }

    /**
     * 移除任务
     *
     * @since 1.0.0
     */
    @Override
    public void removeTask() {
        synchronized (getInstance().getTimer()) {
            //停止任务
            this.stop();
            //置空
            getInstance().setTimer(this.createEmptyTask());
        }
    }

    /**
     * 是否有定时任务正在执行
     *
     * @return 是 /否
     * @since 1.0.0
     */
    @Override
    public boolean isTaskRunning() {
        return !this.isEmptyTaskTimer() && getInstance().getTimer().isRunning();
    }

    /**
     * 判断是否是空任务定时器
     *
     * @return 是 /否
     * @since 1.0.0
     */
    @Override
    public boolean isEmptyTaskTimer() {
        return getInstance().getTimer().getDelay() == 0
               && (getInstance().getTimer().getActionListeners() == null
                   || getInstance().getTimer().getActionListeners().length == 0);
    }

    /**
     * 开始执行任务
     *
     * @since 1.0.0
     */
    @Override
    public void start() {
        getInstance().getTimer().start();
    }

    /**
     * 停止任务
     *
     * @since 1.0.0
     */
    @Override
    public void stop() {
        getInstance().getTimer().stop();
    }

    /**
     * 延迟执行任务
     *
     * @param delayTime delay time
     * @since 1.0.0
     */
    @Override
    public void delay(int delayTime) {
        getInstance().getTimer().setDelay(delayTime);
    }
}
