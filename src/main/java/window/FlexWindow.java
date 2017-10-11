/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

/**
 *
 * @author zua
 */
public class FlexWindow extends Window {

    public FlexWindow(String caption, Component content) {
        super(caption);
        super.setWidth("50%");
        super.setHeightUndefined();
        super.setModal(true);
        super.setStyleName("flex-window");
        super.center();
        super.setContent(content);
    }

}
