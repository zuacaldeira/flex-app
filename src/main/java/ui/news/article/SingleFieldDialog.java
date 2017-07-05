/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news.article;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import ui.SaveButton;
import ui.FlexTextField;

/**
 *
 * @author zua
 */
public class SingleFieldDialog extends VerticalLayout {

    private final FlexTextField textField;
    private final SaveButton save;

    public SingleFieldDialog(String caption) {
        textField = new FlexTextField(caption, null);
        textField.setWidth("100%");
        save = new SaveButton();
        save.addClickListener(click -> {
            // TODO add video url to article
        });            
        AbstractOrderedLayout dialog = new VerticalLayout(textField, save);
        dialog.setHeightUndefined();
        dialog.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        super.addComponents(textField, save);
    }

    public FlexTextField getTextField() {
        return textField;
    }

    public SaveButton getSave() {
        return save;
    }
    
    

}
