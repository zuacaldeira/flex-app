/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.server.ThemeResource;

/**
 *
 * @author zua
 */
public class AmazonFrame extends AdsFrame {

    private static final long serialVersionUID = 1911606664709857426L;

    public AmazonFrame() {
        super("Powered by Amazon", new ThemeResource("html/amazon.html"));
    }
    
}
