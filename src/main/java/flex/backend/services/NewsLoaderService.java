package flex.backend.services;

import flex.backend.db.ApiArticle;
import flex.backend.db.ApiArticles;
import flex.backend.db.ApiSource;
import flex.backend.db.ApiSources;
import flex.backend.db.NewsApiOrg;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 * Created by zua on 15/04/17.
 */
@Stateless
public class NewsLoaderService {


    private Logger logger = Logger.getLogger(
            "NewsLoaderService");
    
    public Map<ApiSource, List<ApiArticle>> loadLatestNews(int howMany) {
        System.out.printf("Populating the database with %s news", howMany);
        Map<ApiSource, List<ApiArticle>> result = new HashMap<>();
        
        // Load sources
        ApiSources sources = NewsApiOrg.GET_ApiSources();

        // Store sources
        for(ApiSource source: sources.getSources()) {
            //sourceService.createOrUpdate(source);
            ApiArticles articles = NewsApiOrg.GET_Articles(source);
            for(ApiArticle article: articles.getArticles()) {
                if(!result.containsKey(source)) {
                    result.put(source, new LinkedList());
                }
                result.get(source).add(article);
                /*if(result.values().size() >= howMany) {
                    return result;
                }*/
            }
        }
        
        return result;
        // database should not have duplicates
    }

    public ApiSources loadSources() {
        return NewsApiOrg.GET_ApiSources();
    }
}
