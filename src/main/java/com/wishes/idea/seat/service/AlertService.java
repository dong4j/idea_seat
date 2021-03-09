package com.wishes.idea.seat.service;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public interface AlertService {
    /**
     * 显示弹窗
     *
     * @param project     当前项目上下文
     * @param timeMinutes 时间（分钟）
     * @since 1.0.0
     */
    void showAlertDialog(Project project, int timeMinutes);

    /**
     * getInstance
     *
     * @return {@link com.wishes.idea.seat.service.impl.AlertServiceImpl}
     * @since 1.0.0
     */
    static AlertService getInstance() {
        if (ApplicationManager.getApplication() != null) {
            return ServiceManager.getService(AlertService.class);
        } else {
            try {
                return (AlertService) AlertService.class.getClassLoader()
                    .loadClass("com.wishes.idea.seat.service.impl.AlertServiceImpl")
                    .newInstance();
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
