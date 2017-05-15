/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class FlexTextField extends TextField {

    public FlexTextField(VaadinIcons icon) {
        super();
        super.setCaption(icon.getHtml());
        super.setCaptionAsHtml(true);
        super.setStyleName(ValoTheme.TEXTFIELD_BORDERLESS 
                + " " + ValoTheme.TEXTFIELD_ALIGN_CENTER
                + " " + ValoTheme.TEXTFIELD_INLINE_ICON
        );
        super.setSizeUndefined();
    }
    
    
}
