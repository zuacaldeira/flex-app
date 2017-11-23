/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.server.Resource;

/**
 *
 * @author zua
 */
public abstract class FlexActionButton extends FlexButton {

    private static final long serialVersionUID = 1703002983017827112L;

    public FlexActionButton(String caption, Resource icon) {
        super(icon);
        super.setDescription(caption);
        super.addStyleName(FlexStyleNames.FLEX_ACTION);
    }
    
}
