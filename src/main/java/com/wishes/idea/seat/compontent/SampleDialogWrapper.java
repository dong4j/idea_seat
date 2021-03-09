package com.wishes.idea.seat.compontent;

import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorEventMulticaster;

/**
 * <p>Description: </p>
 *
 * @author wishes
 * @version 1.0.0
 * @email "mailto:1098832322@qq.com"
 * @date 2021.03.09 13:52
 * @since 1.0.0
 */
public class SampleDialogWrapper implements BaseComponent {

    /** Query listener */
    private QueryListener queryListener;

    /**
     * Init component
     *
     * @since 1.0.0
     */
    @Override
    public void initComponent() {
        this.queryListener = new QueryListener();
        System.out.println("component初始化--------");
        EditorEventMulticaster eventMulticaster = EditorFactory.getInstance().getEventMulticaster();
        eventMulticaster.addEditorMouseMotionListener(this.queryListener);
    }

}
