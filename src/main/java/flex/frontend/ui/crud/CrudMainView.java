/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.crud;

import flex.backend.news.db.GraphEntity;
import flex.frontend.ui.FlexMainView;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class CrudMainView<T extends GraphEntity> extends FlexMainView {

 
    @Override
    public CrudBody<T> getBody() {
        return (CrudBody<T>) super.getBody(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CrudMenu getMenu() {
        return (CrudMenu)super.getMenu(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}