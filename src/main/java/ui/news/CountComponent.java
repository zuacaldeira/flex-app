/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public class CountComponent extends VerticalLayout {

    private Label label;
    private Label number;
    
    public CountComponent(String caption, long number) {
        initLabel(caption);
        initNumber(number);
        
        super.setSizeFull();
        super.setHeightUndefined();
        super.setSpacing(false);
        super.addComponents(this.number, label);
        super.setComponentAlignment(this.number, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(this.label, Alignment.MIDDLE_CENTER);
    }

    public Label getLabel() {
        return label;
    }

    public Label getNumber() {
        return number;
    }

    private void initLabel(String caption) {
        this.label = new Label(caption);
        this.label.setStyleName("label");
        this.label.setSizeUndefined();
    }

    private void initNumber(long number) {
        this.number = new Label(String.valueOf(number));
        this.number.setStyleName("number");
        this.number.setSizeUndefined();
    }
    
    
    
}
