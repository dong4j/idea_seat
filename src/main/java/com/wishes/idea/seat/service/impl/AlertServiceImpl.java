package com.wishes.idea.seat.service.impl;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.wishes.idea.seat.compontent.MessageBuilder;
import com.wishes.idea.seat.constant.Constant;
import com.wishes.idea.seat.service.AlertService;
import com.wishes.idea.seat.ui.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class AlertServiceImpl implements AlertService {

    /** Time */
    private long time;

    /** Notification group */
    private final NotificationGroup NOTIFICATION_GROUP =
        new NotificationGroup("Groovy DSL errors", NotificationDisplayType.BALLOON, true);

    /** Notification */
    private final Notification notification = this.NOTIFICATION_GROUP.createNotification("温馨提醒", "",
                                                                                         NotificationType.INFORMATION,
                                                                                         NotificationListener.URL_OPENING_LISTENER);

    /**
     * 显示弹窗
     *
     * @param project     当前项目上下文
     * @param timeMinutes 时间（分钟）
     * @since 1.0.0
     */
    @Override
    public void showAlertDialog(Project project, int timeMinutes) {
        if (this.time == 0) {
            this.time = System.currentTimeMillis();
        }
        Random random = new Random();
        this.notification.setContent(this.getSubtitle(this.time, System.currentTimeMillis()));
        this.notification.notify(project);
        AlertDialog alertDialog = new AlertDialog(project,
                                                  Constant.Infor.TITLE,
                                                  new MessageBuilder().setBody(Constant.Infor.HARM[random.nextInt(Constant.Infor.HARM.length)]).setVariables(timeMinutes).build());
        alertDialog.show();
    }

    /**
     * 时间计算
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return subtitle
     * @since 1.0.0
     */
    public String getSubtitle(long start, long end) {
        String res = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date parse = format.parse(format.format(new Date(start)));
            Date date = format.parse(format.format(new Date(end)));
            long between = date.getTime() - parse.getTime();
            long day = between / (24 * 60 * 60 * 1000);
            long hour = (between / (60 * 60 * 1000) - day * 24);
            long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
            res = String.format("你已经累计工作 %d 天 %d 小时 %d 分，要起来活动一下。", day, hour, min);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
