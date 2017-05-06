package flex.backend.news.services;

import flex.backend.news.NewsApiOrg;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import flex.backend.news.db.NewsAuthor;
import flex.backend.news.db.Publishes;
import flex.backend.news.db.WrittenBy;
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
    @EJB
    private WritingService writingService;
    @EJB
    private PublishingService publishingService;

    @Schedule(hour = "*", minute = "*/2")
    private void loadNews() {
        Map<NewsSource, Map<NewsAuthor, Collection<NewsArticle>>> data = NewsApiOrg.loadData();
        for(NewsSource source: data.keySet()) {
            for(NewsAuthor author: data.get(source).keySet()) {
                System.out.printf(">>>>>>>> Storing a new Publishes relationship between %s and %s\n", source, author);
                publishingService.createOrUpdate(new Publishes(source, author));
                for(NewsArticle article: data.get(source).get(author)) {
                    System.out.printf(">>>>>>>> Storing a new Writing relationship between %s and %s\n", author, article);
                    writingService.createOrUpdate(new WrittenBy(author, article));
                }
            }
        }
    }

}
