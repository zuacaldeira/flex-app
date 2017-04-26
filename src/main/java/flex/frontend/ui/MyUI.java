package flex.frontend.ui;


import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;

import com.vaadin.ui.*;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;
import flex.backend.db.ApiSources;
import java.util.Collection;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import org.utils.ServiceLocator;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Theme("mytheme")
@Push
public class MyUI extends UI implements SingleSelectionListener {

    public static final int MAX_ARTICLES = 100;
    private VerticalLayout rootLayout;
    private FlexMenu menu;
    private VerticalLayout body;
    private FlexMenu footer;
    
    private ArticlesInfoView articlesInfoView;


    @Override
    protected void init(VaadinRequest request) {
        
        // Load new news
        initMenu();
        initBody();
        initFooter();
        
        rootLayout = new VerticalLayout();
        rootLayout.addComponent(menu);
        rootLayout.addComponent(body);
        
        setContent(rootLayout);
    }

    private void initMenu() {
        menu = new FlexMenu();
        //menu.setHeight("4cm");
    }

    private void initBody() {
        body = new VerticalLayout();
        body.setHeightUndefined();
        body.setStyleName("flex-body");
        body.setMargin(new MarginInfo(true, false, false, false));
        

        articlesInfoView = new ArticlesInfoView();
        menu.getSourcesComboBox().addSelectionListener(this);
        menu.getCategoryComboBox().addSelectionListener(this);
        menu.getLanguageComboBox().addSelectionListener(this);
        
        body.addComponent(articlesInfoView);
        
        /* World News tab */
        /* Full-size CSS Layout root component */


    }

    private void initFooter() {
        footer = new FlexMenu();
        footer.setHeight("1cm");
        footer.setWidth("100%");
    }

    @Override
    public void selectionChange(SingleSelectionEvent event) {
        articlesInfoView.removeAllComponents();
        
        if(event.getComponent() == menu.getSourcesComboBox()) {
            SingleSelect<ApiSource> ss = event.getSource();
            ApiSource apiSource = ss.getValue();
            
            if(apiSource == null) {
                Map<ApiSource, Collection<ApiArticle>> articles = ServiceLocator.findNewsLoaderService().loadArticles(MAX_ARTICLES);
                for(ApiSource s: articles.keySet()) {
                    articlesInfoView.addArticles(s, articles.get(s));
                }
            }
            else {
                Collection<ApiArticle> articles = ServiceLocator.findNewsLoaderService().loadArticles(apiSource);
                articlesInfoView.addArticles(apiSource, articles);
            }
        }
        
        else if(event.getComponent() == menu.getCategoryComboBox()) {
            SingleSelect<String> selection = event.getSource();
            String category = selection.getValue();
            if(category == null) {
                
            } else {
                ApiSources sources = ServiceLocator.findNewsLoaderService().loadSourcesWithCategory(category);
                sources.getSources().forEach(s -> {
                    articlesInfoView.addArticles(s, ServiceLocator.findNewsLoaderService().loadArticles(s));
                });
            }
        }
        
        else if(event.getComponent() == menu.getLanguageComboBox()) {
            SingleSelect<String> selection = event.getSource();
            String language = selection.getValue();
            if(language == null) {
                
            } else {
                ApiSources sources = ServiceLocator.findNewsLoaderService().loadSourcesWithLanguage(language);
                sources.getSources().forEach(s -> {
                    articlesInfoView.addArticles(s, ServiceLocator.findNewsLoaderService().loadArticles(s));
                });
            }
        }

        articlesInfoView.setHeightUndefined();
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}