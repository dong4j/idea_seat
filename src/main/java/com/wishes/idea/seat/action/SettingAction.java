package com.wishes.idea.seat.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.wishes.idea.seat.compontent.QueryListener;
import com.wishes.idea.seat.constant.Constant;
import com.wishes.idea.seat.service.AlertService;
import com.wishes.idea.seat.service.ScheduledService;
import com.wishes.idea.seat.ui.SettingDialog;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class SettingAction extends AnAction {

    /** isStart */
    public static boolean isStart = false;

    /**
     * Action performed
     *
     * @param e e
     * @since 1.0.0
     */
    @Override
    public void actionPerformed(AnActionEvent e) {
        //拿到项目上下文
        Project p = e.getProject();
        //创建设置窗口
        String result = new SettingDialog(p, Constant.Settings.SETTING_WINDOW_TITLE
            , Constant.Settings.SETTING_TIME_SELECTOR_TEXT
            , Constant.Settings.TIME_SELECT_ARRAY, new InputValidator() {
            @Override
            public boolean checkInput(String s) {
                try {
                    long minute = Long.parseLong(s);
                    //最大值为480分钟
                    return minute > 0 && minute <= 480;
                } catch (Exception e) {
                    //输入异常
                }
                return false;
            }

            @Override
            public boolean canClose(String s) {
                return false;
            }
        }).createSettingDialog();

        try {
            int period = Integer.parseInt(result);
            ScheduledService.getInstance().addTask(period * 60 * 1000, e1 -> ProgressManager.getInstance()
                .executeNonCancelableSection(
                    () -> AlertService.getInstance().showAlertDialog(p, period)));
            //开始运行
            ScheduledService.getInstance().start();
        } catch (Exception ex) {
            //exception
            ex.printStackTrace();
        }
        isStart = true;
        ScheduledExecutorService service = Executors
            .newSingleThreadScheduledExecutor();
        AtomicInteger count = new AtomicInteger();
        service.scheduleAtFixedRate(() -> {
            if (QueryListener.flag) {
                QueryListener.flag = false;
                count.set(0);
            } else {
                count.getAndIncrement();
                if (count.get() > 5) {
                    ScheduledService.getInstance().removeTask();
                }
            }
        }, 5, 60, TimeUnit.SECONDS);


    }
}
