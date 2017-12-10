/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;

/**
 *
 * @author zua
 */
public class FacebookFollowUsComponent extends BrowserFrame {

    private static final long serialVersionUID = 3486777831042559114L;


    public FacebookFollowUsComponent() {
        super(null, new ExternalResource("https://www.facebook.com/plugins/follow.php?href=https%3A%2F%2Fwww.facebook.com%2FConnectTheUnconnectedDots%2F&width=32&height=21&layout=button_count&size=small&show_faces=false&appId=311537906014506"));
        super.setWidth("90px");
        super.setHeight("32px");
    }
    
}
