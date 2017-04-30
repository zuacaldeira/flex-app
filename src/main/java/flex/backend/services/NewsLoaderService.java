package flex.backend.services;

import flex.backend.db.ApiArticle;
import flex.backend.db.ApiArticles;
import flex.backend.db.ApiSource;
import flex.backend.db.ApiSources;
import flex.backend.db.NewsApiOrg;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;

/**
 * Created by zua on 15/04/17.
 */
@Singleton
public class NewsLoaderService {
    
    @Resource
    private TimerService timer;
    
    private Map<ApiSource, Collection<ApiArticle>> sourcesAndArticles;

    @PostConstruct
    private void initSourcesAndArticles() {
        // Load sources
        System.out.println("===> Loading data from newsapi.org...");
        this.sourcesAndArticles = new TreeMap<>();
        ApiSources sources = NewsApiOrg.GET_ApiSources();

        // Store sources
        for(ApiSource source: sources.getSources()) {
            //sourceService.createOrUpdate(source);
            ApiArticles articles = NewsApiOrg.GET_Articles(source);
            for(ApiArticle article: articles.getArticles()) {
                if(!sourcesAndArticles.containsKey(source)) {
                    sourcesAndArticles.put(source, new LinkedList());
                }
                sourcesAndArticles.get(source).add(article);
            }
        }
        System.out.println("<=== Data loaded.");
    }
    
    
    @Schedule(hour = "*", minute = "*/5")
    public void reload() {
        initSourcesAndArticles();        
    }
    
    
    
    public Map<ApiSource, Collection<ApiArticle>> loadArticles(int howMany) {
        // Load sources
        Map<ApiSource, Collection<ApiArticle>> result = new TreeMap<>();

        // Store sources
        for(ApiSource source: sourcesAndArticles.keySet()) {
            Collection<ApiArticle> articles = sourcesAndArticles.get(source);
            for(ApiArticle article: articles) {
                if(!result.containsKey(source)) {
                    result.put(source, new LinkedList());
                }
                result.get(source).add(article);
                if(result.values().size() >= howMany) {
                    return result;
                }
            }
        }
        return result;
    }
    
    public Collection<ApiArticle> loadArticles(ApiSource source) {
        return sourcesAndArticles.get(source);
    }


    public ApiSources loadSources() {
        ApiSources result = new ApiSources();
        sourcesAndArticles.forEach((k,v) -> {
            if(!result.containsSource(k)) {
                result.addSource(k);
            }
        });
        return result;
    }
    
    public ApiSources loadSourcesWithCategory(String category) {
        ApiSources result = new ApiSources();
        sourcesAndArticles.forEach((k,v) -> {
            if(k.getCategory() != null && k.getCategory().equals(category) && !result.containsSource(k)) {
                result.addSource(k);
            }
        });
        return result;
    }

    public ApiSources loadSourcesWithLanguage(String language) {
        ApiSources result = new ApiSources();
        sourcesAndArticles.forEach((k,v) -> {
            if(k.getLanguage() != null && k.getLanguage().equals(language) && !result.containsSource(k)) {
                result.addSource(k);
            }
        });
        return result;
    }

    public ApiSources loadSourcesWithCountry(String country) {
        ApiSources result = new ApiSources();
        sourcesAndArticles.forEach((k,v) -> {
            if(k.getCountry() != null 
                    && k.getCountry().equals(country) 
                    && !result.containsSource(k)) {
                result.addSource(k);
            }
        });
        return result;
    }

    public List<String> loadCategories() {
        List<String> categories = new LinkedList<>();
        sourcesAndArticles.keySet().forEach(s -> {
            if(!categories.contains(s.getCategory())) {
                categories.add(s.getCategory());
            }
        });
        
        return categories;
    }

    public List<String> loadLanguages() {
        List<String> languages = new LinkedList<>();        
        sourcesAndArticles.keySet().forEach(s -> {
            if(!languages.contains(s.getLanguage())) {
                languages.add(s.getLanguage());
            }
        });
        
        return languages;
    }
    
    public List<String> loadCountries() {
        List<String> countries = new LinkedList<>();
        sourcesAndArticles.keySet().forEach(s -> {
            if(!countries.contains(s.getCountry())) {
                countries.add(s.getCountry());
            }
        });
        
        return countries;
    }
    
    
}
