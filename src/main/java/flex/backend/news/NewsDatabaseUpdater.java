package flex.backend.news;

import flex.backend.news.db.ApiArticle;
import flex.backend.news.db.ApiSource;
import flex.backend.news.db.Author;
import flex.backend.news.services.NewsSourceService;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;

/**
 * Created by zua on 15/04/17.
 */
@Singleton
public class NewsDatabaseUpdater {
    
    @Resource
    private TimerService timer;
    
    
    @EJB
    private NewsSourceService sourcesService;


    @Schedule(hour = "*", minute = "*/1")
    private void loadNews() {
        System.out.println("===> Loading data from newsapi.org...");

        Map<ApiSource, Map<Author, Collection<ApiArticle>>> data = NewsApiOrg.loadData();
        for(ApiSource source: data.keySet()) {
            storeAll(source, data.get(source));
        }
        
        System.out.println("<=== Data loaded.");
    }

    private void storeAll(ApiSource source, Map<Author, Collection<ApiArticle>> map) {
        if(sourcesService.find(source.getSourceId()) == null) {
            for(Author author: map.keySet()) {
                source.addAuthor(author);
                if(map.get(author) != null) {
                    for(ApiArticle article: map.get(author)) {
                        author.addArticle(article);
                    }
                } else {
                    System.out.println("Null Actor!!!");
                }
            }
            sourcesService.createOrUpdate(source);
        }
    }
    
}
