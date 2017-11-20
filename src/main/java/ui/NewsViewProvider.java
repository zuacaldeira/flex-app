/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.navigator.Navigator.ClassBasedViewProvider;
import view.main.FlexNewsView;

/**
 *
 * @author zua
 */
public class NewsViewProvider extends ClassBasedViewProvider {

    private static final long serialVersionUID = -7126689158013262916L;

    public NewsViewProvider() {
        super(FlexViews.NEWS, FlexNewsView.class);
    }
    
    
}
