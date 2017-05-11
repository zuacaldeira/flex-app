/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class CountComponent extends VerticalLayout {

    private Label label;
    private Label number;
    
    public CountComponent(String caption, long number) {
        this.label = new Label(caption);
        this.label.setStyleName(ValoTheme.LABEL_H1);
        this.number = new Label(String.valueOf(number));
        this.number.setStyleName(ValoTheme.LABEL_H2);
        setStyleName("count");
        setSizeFull();
    }

    public Label getLabel() {
        return label;
    }

    public Label getNumber() {
        return number;
    }
    
    
    
}
