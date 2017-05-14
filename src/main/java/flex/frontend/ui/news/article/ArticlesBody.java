package flex.frontend.ui.news.article;

import com.vaadin.event.LayoutEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.services.NewsArticleService;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class ArticlesBody extends FlexBody {

    private Panel summaries;
    private BrowserFrame browserFrame;
    private ArticleView selected;
    
    public ArticlesBody() {
        initSummaries();
        initBrowserFrame();
        super.getLayout().addComponents(summaries, browserFrame);
        super.getLayout().setExpandRatio(summaries, .25f);
        super.getLayout().setExpandRatio(browserFrame, .75f);
        super.addStyleName("articles");
    }

    private void initSummaries() {
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setSpacing(true);

        summaries = new FlexPanel("Latest News", panelContent);
        summaries.setStyleName("summaries");

        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
        Iterable<NewsArticle> articles = service.findAll();
        
        articles.forEach(article -> {
            ArticleView articleView = FlexViewFactory.getInstance().createArticleView(article);
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
            panelContent.addComponents(articleView, new FlexDiv());
            
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
            selected.minimizeInfo();
        }
        selected = articleView;
        selected.maximizeInfo();
        //summaries.setScrollTop();
    }

}
