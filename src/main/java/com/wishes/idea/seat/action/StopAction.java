package com.wishes.idea.seat.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
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
public class StopAction extends AnAction {

    /**
     * Action performed
     *
     * @param e e
     * @since 1.0.0
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        boolean flag = false;
        if (!ScheduledService.getInstance().isEmptyTaskTimer()) {
            ScheduledService.getInstance().removeTask();
            flag = true;
        }
        SettingAction.isStart = false;
        Messages.showDialog(flag ? Constant.Stop.SUCCESS_TEXT : Constant.Stop.FAIL_TEXT, Constant.Stop.TITLE,
                            new String[] {"确定"}, 0,
                            Messages.getInformationIcon());
    }
}
