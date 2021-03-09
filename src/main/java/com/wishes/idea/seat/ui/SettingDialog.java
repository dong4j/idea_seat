package com.wishes.idea.seat.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.MultiLineLabelUI;
import com.wishes.idea.seat.compontent.StorageComponent;
import com.wishes.idea.seat.constant.Constant;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class SettingDialog extends DialogWrapper {
    /**
     * 项目
     */
    private Project project;

    /**
     * 窗内文字
     */
    private String modelText;
    /** Time text */
    private final String timeText;

    /**
     * 备选值
     */
    private String[] modelSelectedValues;

    /**
     * 时间备选值
     */
    private final String[] timeSelectedValues;

    /**
     * 默认选中模型
     */
    private final String defaultModelValue;

    /**
     * 默认选中时间
     */
    private final String defaultTimeValue;

    /**
     * 参数校验器
     */
    private final InputValidator inputValidator;

    /**
     * 模型和时间选择框
     */
    private ComboBox<String> timeSelector, /** Model selector */
    modelSelector;

    /**
     * Setting dialog
     *
     * @param project            project
     * @param title              title
     * @param timeText           time text
     * @param timeSelectedValues time selected values
     * @param inputValidator     input validator
     * @since 1.0.0
     */
    public SettingDialog(@Nullable Project project, String title, String timeText
        , String[] timeSelectedValues
        , InputValidator inputValidator) {
        super(project);
        this.setTitle(title);
        this.timeText = timeText;
        this.timeSelectedValues = this.isEmpty(timeSelectedValues) ? this.initTimeSelectValues() : timeSelectedValues;
        this.defaultModelValue = this.readFromStorage(true);
        this.defaultTimeValue = this.readFromStorage(false);
        this.inputValidator = inputValidator;
        this.init();
    }

    /**
     * Sets project *
     *
     * @param project project
     * @return the project
     * @since 1.0.0
     */
    public SettingDialog setProject(Project project) {
        this.project = project;
        return this;
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
        JPanel panel = this.createIconPanel();

        //时间选择panel
        JPanel timeSelectorPanel = this.createMessagePanel(this.timeText);
        this.timeSelector = new ComboBox<>(220);
        this.timeSelector.setEditable(true);
        this.timeSelector.setModel(new DefaultComboBoxModel<>(this.timeSelectedValues));
        this.timeSelector.getEditor().setItem(this.defaultTimeValue);
        this.timeSelector.setSelectedItem(this.defaultTimeValue);
        timeSelectorPanel.add(this.timeSelector, "Center");
        panel.add(timeSelectorPanel, "South");
        return panel;
    }

    /**
     * 创建一个带有图标的panel
     *
     * @return 图标panel j panel
     * @since 1.0.0
     */
    @NotNull
    protected JPanel createIconPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        JLabel iconLabel = new JLabel(Messages.getQuestionIcon());
        Container container = new Container();
        container.setLayout(new BorderLayout());
        container.add(iconLabel, "North");
        panel.add(container, "West");

        return panel;
    }

    /**
     * Create message panel
     *
     * @param text text
     * @return the j panel
     * @since 1.0.0
     */
    @NotNull
    protected JPanel createMessagePanel(String text) {
        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel textLabel = new JLabel(text);
        textLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        textLabel.setUI(new MultiLineLabelUI());
        messagePanel.add(textLabel, "North");
        return messagePanel;
    }

    /**
     * 获得选择的时间
     *
     * @return 提醒时间 time selector value
     * @since 1.0.0
     */
    private String getTimeSelectorValue() {
        Object o = this.timeSelector.getSelectedItem();
        return o != null ? o.toString() : "";
    }


    /**
     * 创建设置窗口
     *
     * @return 点击 “ok” 按钮后返回所选中的值
     * @since 1.0.0
     */
    public String createSettingDialog() {
        this.show();
        String time = this.getTimeSelectorValue();
        this.saveToStorage(time);
        return time;
    }

    /**
     * 从存储中读取
     *
     * @param isModel 是否是读取模型
     * @return 存储的值或默认生成备选的第一项的值 string
     * @since 1.0.0
     */
    private String readFromStorage(boolean isModel) {
        return StorageComponent.getValue("lastSelectedMinutes") == null
               ? this.isEmpty(this.timeSelectedValues) ? "" : this.timeSelectedValues[0]
               : StorageComponent.getValue("lastSelectedMinutes");

    }

    /**
     * 存储
     *
     * @param time 选取的时间值
     * @since 1.0.0
     */
    private void saveToStorage(String time) {
        StorageComponent.save("lastSelectedMinutes", time);
    }

    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 是 /否
     * @since 1.0.0
     */
    private boolean isEmpty(String[] array) {
        return array == null || array.length == 0;
    }


    /**
     * 初始化时间下拉框选择
     *
     * @return initValues string [ ]
     * @since 1.0.0
     */
    private String[] initTimeSelectValues() {
        return Constant.Settings.TIME_SELECT_ARRAY;
    }

}
