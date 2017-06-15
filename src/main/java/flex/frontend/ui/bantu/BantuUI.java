/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu;

import flex.frontend.ui.SecuredUI;
import flex.frontend.ui.bantu.videos.VideoMainView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinServlet;
import flex.frontend.ui.FlexMainView;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author zua
 */
@Theme("mytheme")
@Push
public class BantuUI extends SecuredUI {

    @WebServlet(urlPatterns = "/bantus/*", name = "BantuUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = BantuUI.class, productionMode = false)
    public static class BantuUIServlet extends VaadinServlet {
    }

    @Override
    protected String getPageLocation() {
        return "/flex-app/bantus";
    }
    
    @Override
    protected FlexMainView createMainView() {
        String currentLocation = Page.getCurrent().getLocation().getPath();
        System.out.println("currentLocation -> " + currentLocation);
        if(currentLocation.endsWith("videos")) {
            return new VideoMainView();
        }
        return new BantuMainView();
    }
    
}
