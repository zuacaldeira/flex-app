/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news.crawling;

import db.news.NewsSource;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import services.news.NewsSourceService;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
@Singleton
public class DBCompletionCrawler {
    
    private FlexLogger logger = new FlexLogger(DBCompletionCrawler.class);
        
    @EJB
    private NewsSourceService sourcesService;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Schedule(hour = "*", minute="*/5")
    public void completeLogos() {
        List<NewsSource> sources = sourcesService.findAll("logoUrl", null);
        logger.info("FFFFFFFFFF Found %d sources without logo", sources.size());
        
        sources.forEach(s -> {
            if(s.getLogoUrl() == null || s.getLogoUrl().isEmpty()) {
                logger.info("FFFFFFFFFF Found %s ", s);        
            }
        });
    }
}
