package flex.frontend.ui.news;

import com.neovisionaries.i18n.CountryCode;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.GridLayout;
import flex.backend.news.db.ApiArticle;
import flex.backend.news.db.ApiSource;
import flex.frontend.ui.FlexViewFactory;
import java.util.Collection;
import org.utils.ServiceLocator;
import org.vaadin.alump.maplayout.MapColors;
import org.vaadin.alump.maplayout.WorldMap;

/**
 * Created by zua on 13/04/17.
 */
public class ArticlesInfoView extends GridLayout {
    
    public ArticlesInfoView() {
        super(1,1);
        setWidth("100%");
        setStyleName("articles");
        //initArticles();
        setMargin(true);
        setSpacing(true);
    }

    private void initArticles() {
        ServiceLocator.findNewsArticleService().findAll()
            .forEach((article) -> {
                addComponent(FlexViewFactory.createArticleView(article));
            }
        );
    }

    public void addArticles(Collection<ApiArticle> articles) {
        articles.stream().forEach((article) -> {
            addComponent(FlexViewFactory.createArticleView(article));
        });
    }

    private WorldMap getCountryMinuature(ApiSource source, ApiArticle article) {
        WorldMap map = new WorldMap();
        map.addStyleName("smooth-color-transition");
        map.setAddTitles(false);
        map.setStyleNamesToItems(MapColors.GREEN, CountryCode.FI, CountryCode.US, CountryCode.DE);
        map.setStyleNamesToItems(MapColors.YELLOW, CountryCode.AR, CountryCode.PL);
        map.setWidth(100, Unit.PERCENTAGE);
        return map;
    }

}
