/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combo;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Collection;

/**
 *
 * @author zua
 * @param <T> The type of the object in the combo box
 */
public abstract class FlexComboBox<T> extends ComboBox<T>{

    public FlexComboBox() {
        init();
    }

    public FlexComboBox(String caption) {
        super(caption);
        init();
    }

    public FlexComboBox(String caption, Collection<T> options) {
        super(caption, options);
        init();
    }

    private void init() {
        setSizeUndefined();
        setStyleName(ValoTheme.COMBOBOX_SMALL);
    }

    
}
