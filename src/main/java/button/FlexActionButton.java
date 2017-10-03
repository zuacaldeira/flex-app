/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import com.vaadin.server.Resource;

/**
 *
 * @author zua
 */
public abstract class FlexActionButton extends FlexButton {

    public FlexActionButton(String caption, Resource icon) {
        super(icon);
        super.setDescription(caption);
        super.addStyleName(FlexStyleNames.FLEX_ACTION);
    }
    
}
