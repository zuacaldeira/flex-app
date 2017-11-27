/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.navigator.Navigator.ClassBasedViewProvider;
import org.ngutu.ui.news.NewsView;

/**
 *
 * @author zua
 */
public class WelcomeViewProvider extends ClassBasedViewProvider {

    private static final long serialVersionUID = -7126689158013262916L;

    public WelcomeViewProvider() {
        super(FlexViews.WELCOME, NewsView.class);
    }
    
    
}
