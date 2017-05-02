package flex.frontend.ui.news;


import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import flex.frontend.ui.FlexMenu;
import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Theme("mytheme")
@Push
public class NewsUI extends UI {

    public static final int MAX_ARTICLES = 100;
    private AbsoluteLayout rootLayout;
    private FlexMenu menu;
    private Panel body;
    private FlexMenu footer;
    
    private ArticlesInfoView articlesInfoView;


    @Override
    protected void init(VaadinRequest request) {
        
        // Load new news
        initMenu();
        initBody();
        initFooter();
        
        rootLayout = new AbsoluteLayout();
        rootLayout.addComponent(body, "top:2cm");
        rootLayout.addComponent(menu);
        rootLayout.setHeight("1080px");
        
        setContent(rootLayout);
    }

    private void initMenu() {
        menu = new FlexMenu();
        //menu.getSourcesComboBox().addSelectionListener(this);
        //menu.getCategoryComboBox().addSelectionListener(this);
        //menu.getLanguageComboBox().addSelectionListener(this);
        //menu.setHeight("4cm");
    }

    private void initBody() {
        articlesInfoView = new ArticlesInfoView();        
        body = new Panel(null, articlesInfoView);
        body.setSizeFull();
        body.setStyleName(ValoTheme.PANEL_BORDERLESS);
        
        /* World News tab */
        /* Full-size CSS Layout root component */


    }

    private void initFooter() {
        footer = new FlexMenu();
        footer.setHeight("1.5cm");
        footer.setWidth("100%");
    }




    @WebServlet(urlPatterns = "/news/*", name = "NewsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NewsUI.class, productionMode = false)
    public static class NewsUIServlet extends VaadinServlet {
    }

}