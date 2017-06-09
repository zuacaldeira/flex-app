package flex.frontend.ui.news;

import com.vaadin.event.LayoutEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.services.NewsArticleService;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import flex.frontend.ui.news.article.ArticleView;
import flex.frontend.ui.news.article.FlexPanel;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class NewsBody extends FlexBody {

    private HorizontalSplitPanel splitPanel;
    private Panel summaries;
    private BrowserFrame browserFrame;
    private ArticleView selected;
    private Object myData;
    
    public NewsBody() {
        initSummaries();
        initBrowserFrame();
        initSplitPanel();
        super.getLayout().addComponent(splitPanel);
        super.addStyleName("articles");
    }

    public NewsBody(Object data) {
        this.myData = data;
        initSummaries();
        initBrowserFrame();
        initSplitPanel();
        super.getLayout().addComponent(splitPanel);
        super.addStyleName("articles");
    }

    private void initSummaries() {
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setSpacing(false);
        panelContent.setMargin(false);

        summaries = new FlexPanel(null, panelContent);
        summaries.setStyleName(ValoTheme.PANEL_BORDERLESS + " " + "summaries");

        Iterable<NewsArticle> articles = loadArticles();
        
        articles.forEach(article -> {
            ArticleView articleView = FlexViewFactory.getInstance().createArticleView(article);
            articleView.minimize();
            articleView.addLayoutClickListener((LayoutEvents.LayoutClickEvent event) -> {
                if(article.getUrl() != null) {
                    browserFrame.setSource(new ExternalResource(article.getUrl()));
                    updateSelected(articleView);
                }
            });
            // First initialization
            if(selected == null) {
                updateSelected(articleView);
            }
            panelContent.addComponents(articleView);
        });
    }

    private void initBrowserFrame() {
        browserFrame = new BrowserFrame();
        browserFrame.setSizeFull();
        if(selected != null && selected.getArticle().getUrl() != null) {
            browserFrame.setSource(new ExternalResource(selected.getArticle().getUrl()));
        }
    }

    private void updateSelected(ArticleView articleView) {
        if(selected != null) {
            selected.minimize();
        }
        selected = articleView;
        selected.maximizeInfo();
    }

    
    private void initSplitPanel() {
        splitPanel = new HorizontalSplitPanel(summaries, browserFrame);
        splitPanel.setSizeFull();
        splitPanel.setSplitPosition(25f);
    }

    public Iterable<NewsArticle> loadArticles() {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
        return service.findAll();
    }

    public Object getMyData() {
        return myData;
    }

    public BrowserFrame getBrowserFrame() {
        return browserFrame;
    }
    
    
    

    
}
