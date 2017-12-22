/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

/**
 *
 * @author zua
 */
public class FlexWindow extends Window {

    private static final long serialVersionUID = -1240553493353118497L;

    public FlexWindow(String caption, Component content) {
        this(caption);
        super.setContent(content);
    }

    public FlexWindow(String caption) {
        super(caption);
        super.setWidth("50%");
        super.setHeight("75%");
    }

}
