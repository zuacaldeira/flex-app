/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.ECS.client.jax.Item;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class AmazonItemView extends CssLayout {

    private static final long serialVersionUID = -5396320185588175550L;
    private final Item item;

    public AmazonItemView(Item item) {
        this.item = item;
        super.addComponent(new Label(item.getASIN()));
    }

    public Item getItem() {
        return item;
    }
    
    
    
}
