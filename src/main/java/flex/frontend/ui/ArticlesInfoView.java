package flex.frontend.ui;

import com.neovisionaries.i18n.CountryCode;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.utils.ServiceLocator;
import org.vaadin.alump.maplayout.MapColors;
import org.vaadin.alump.maplayout.WorldMap;

/**
 * Created by zua on 13/04/17.
 */
public class ArticlesInfoView extends HorizontalLayout {
    private List<VerticalLayout> lists;
    private int current;
    private final int N_LISTS = 3;
    
    public ArticlesInfoView() {
        setWidth("100%");
        
        setStyleName("articles");
        initLists();
        initArticles();
        setMargin(true);
        setSpacing(true);
    }

    private void initArticles() {
        Map<ApiSource, Collection<ApiArticle>> news = ServiceLocator.findNewsLoaderService().loadArticles(NewsUI.MAX_ARTICLES);
        news.keySet().stream().forEach((source) -> {
            news.get(source).stream().forEach((article) -> {
                lists.get(current).addComponent(FlexViewFactory.createArticleView(source, article));
                updateCurrent();
            });
        });
    }

    public void addArticles(ApiSource source, Collection<ApiArticle> articles) {
        articles.stream().forEach((article) -> {
            lists.get(current).addComponent(FlexViewFactory.createArticleView(source, article));
            updateCurrent();
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

    private void initLists() {
        lists = new ArrayList<>();
        for(int i = 0; i < N_LISTS; i++) {
            VerticalLayout v = new VerticalLayout();
            v.setSizeFull();
            lists.add(v);
            addComponent(v);
        }
        current = 0;
    }

    private void updateCurrent() {
        if(current == lists.size()-1) {
            current = 0;
        }
        else {
            current++;
        }
    }


}
