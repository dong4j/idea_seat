package com.wishes.idea.seat.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.wishes.idea.seat.constant.Constant;
import com.wishes.idea.seat.service.ScheduledService;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Random;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;


/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class AlertDialog extends DialogWrapper {
    /**
     * 窗体文本
     */
    private final String text;

    /** Ok action */
    private CustomOKAction okAction;
    /** Exit action */
    private CustomRestartAction exitAction;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * Alert dialog
     *
     * @param project project
     * @param title   title
     * @param text    text
     * @since 1.0.0
     */
    public AlertDialog(@Nullable Project project, String title, String text) {
        super(project, false, true);
        this.setTitle(title);
        this.text = text;
        this.init();
    }

    /**
     * Create center panel
     *
     * @return the j component
     * @since 1.0.0
     */
    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return this.createFrame();
    }

    /**
     * Create actions
     *
     * @return the action [ ]
     * @since 1.0.0
     */
    @NotNull
    @Override
    protected Action[] createActions() {
        this.exitAction = new CustomRestartAction("休息一会,命重要");
        this.okAction = new CustomOKAction("再战五分钟");
        // 设置默认的焦点按钮
        this.exitAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[] {this.okAction, this.exitAction};
    }

    /**
     * 再战
     *
     * @author wishes
     * @version 1.0.0
     * @email "mailto:1098832322@qq.com"
     * @date 2021.03.09 13:52
     * @return {@link JComponent}
     * @since 1.0.0
     */
    protected class CustomOKAction extends DialogWrapperAction {

        private static final long serialVersionUID = 2608575338725440271L;

        /**
         * Custom ok action
         *
         * @param name name
         * @since 1.0.0
         */
        protected CustomOKAction(@NotNull String name) {
            super(name);
        }

        /**
         * Do action
         *
         * @param actionEvent action event
         * @since 1.0.0
         */
        @Override
        protected void doAction(ActionEvent actionEvent) {
            // 点击ok的时候进行数据校验
            ScheduledService.getInstance().removeTask();
            ScheduledService.getInstance().addTask(Constant.Infor.FIGHT_TIME, false);

            AlertDialog.this.close(CANCEL_EXIT_CODE);
        }
    }

    /**
     * 休息
     *
     * @author wishes
     * @version 1.0.0
     * @email "mailto:1098832322@qq.com"
     * @date 2021.03.09 13:52
     * @return {@link JComponent}
     * @since 1.0.0
     */
    protected class CustomRestartAction extends DialogWrapperAction {

        private static final long serialVersionUID = -504667664497323259L;

        /**
         * Custom restart action
         *
         * @param name name
         * @since 1.0.0
         */
        protected CustomRestartAction(@NotNull String name) {
            super(name);
        }

        /**
         * Do action
         *
         * @param actionEvent action event
         * @since 1.0.0
         */
        @Override
        protected void doAction(ActionEvent actionEvent) {
            // 点击ok的时候进行数据校验
            ScheduledService.getInstance().removeTask();
            ScheduledService.getInstance().addTask(Constant.Infor.REST_TIME, true);
            AlertDialog.this.close(CANCEL_EXIT_CODE);
        }
    }

    /**
     * Show
     *
     * @since 1.0.0
     */
    @Override
    public void show() {
        if (Messages.isMacSheetEmulation()) {
            this.setInitialLocationCallback(() -> {
                JRootPane rootPane = SwingUtilities.getRootPane(this.getWindow().getParent());
                if (rootPane == null) {
                    rootPane = SwingUtilities.getRootPane(this.getWindow().getOwner());
                }

                Point p = rootPane.getLocationOnScreen();
                p.x += (rootPane.getWidth() - this.getWindow().getWidth()) / 2;
                return p;
            });
            this.getPeer().getWindow().setOpacity(0.8F);
            this.setAutoAdjustable(false);
            this.setSize(this.getPreferredSize().width, 0);
        }

        super.show();
    }

    /**
     * 创建容器
     *
     * @return {@link JComponent}
     * @since 1.0.0
     */
    private JComponent createFrame() {
        Random random = new Random();
        int index = random.nextInt(99) + 1;
        JPanel panel = new JPanel(new BorderLayout());
        if (index % 2 == 0) {
            JLabel text = new JLabel(this.text);
            text.setFont(new Font("微软雅黑", Font.BOLD, 15));
            //添加进容器
            panel.add(text, BorderLayout.CENTER);
            panel.setSize(600, 300);
        } else {
            Constant.AlertDialog[] values = Constant.AlertDialog.values();
            int num = random.nextInt(values.length);
            URL iconPath = this.getClass().getResource(values[num].getImagePath());
            JLabel label = new JLabel(new ImageIcon(iconPath));
            JLabel text = new JLabel(values[num].getText());
            this.setTitle("运动一下");
            text.setFont(new Font("微软雅黑", Font.BOLD, 15));
            //添加进容器
            panel.add(text, BorderLayout.PAGE_END);
            panel.add(label, BorderLayout.CENTER);
        }

        return panel;
    }
}
