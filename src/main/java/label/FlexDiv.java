/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package label;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class FlexDiv extends Label {

    public FlexDiv() {
        super("<hr />", ContentMode.HTML);
        super.setWidth("100%");
        super.setHeight("4px");
    }
    
}
