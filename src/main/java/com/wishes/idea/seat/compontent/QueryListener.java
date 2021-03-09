package com.wishes.idea.seat.compontent;

import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.wishes.idea.seat.action.SettingAction;
import com.wishes.idea.seat.constant.Constant;
import com.wishes.idea.seat.service.ScheduledService;

import org.jetbrains.annotations.NotNull;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class QueryListener implements EditorMouseMotionListener {
    /** Before mouse */
    private long beforeMouse = Long.MAX_VALUE;

    /** flag */
    public static boolean flag = false;

    /**
     * Mouse moved
     *
     * @param e e
     * @since 1.0.0
     */
    @Override
    public void mouseMoved(@NotNull EditorMouseEvent e) {
        long now = System.currentTimeMillis();
        flag = true;
        if (SettingAction.isStart && now - this.beforeMouse > Constant.Infor.STOP_TIME && !ScheduledService.getInstance().isTaskRunning()) {
            ScheduledService.getInstance().addTask(0, true);
        }
        this.beforeMouse = now;
    }
}
