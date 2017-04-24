package flex.backend.services;

import flex.backend.db.ApiArticle;
import flex.backend.db.ApiArticles;
import flex.backend.db.ApiSource;
import flex.backend.db.ApiSources;
import flex.backend.db.NewsApiOrg;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 * Created by zua on 15/04/17.
 */
@Stateless
public class NewsLoaderService {
    
    public Map<ApiSource, List<ApiArticle>> loadArticles(int howMany) {
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
    }
    
    public Collection<ApiArticle> loadArticles(ApiSource source) {
        return NewsApiOrg.GET_Articles(source).getArticles();
    }


    public ApiSources loadSources() {
        return NewsApiOrg.GET_ApiSources();
    }
    
    public List<String> loadCategories() {
        List<String> categories = new LinkedList<>();
        
        ApiSources sources = NewsApiOrg.GET_ApiSources();
        sources.getSources().forEach(s -> {
            if(!categories.contains(s.getCategory())) {
                categories.add(s.getCategory());
            }
        });
        
        return categories;
    }

    public List<String> loadLanguages() {
        List<String> languages = new LinkedList<>();
        
        ApiSources sources = NewsApiOrg.GET_ApiSources();
        sources.getSources().forEach(s -> {
            if(!languages.contains(s.getLanguage())) {
                languages.add(s.getLanguage());
            }
        });
        
        return languages;
    }
    
    public List<String> loadCountries() {
        List<String> countries = new LinkedList<>();
        
        ApiSources sources = NewsApiOrg.GET_ApiSources();
        sources.getSources().forEach(s -> {
            if(!countries.contains(s.getCountry())) {
                countries.add(s.getCountry());
            }
        });
        
        return countries;
    }
    
    
}
