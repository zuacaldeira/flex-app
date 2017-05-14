package flex.backend.news.services;

import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import flex.backend.news.db.NewsAuthor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zua on 15/04/17.
 */
@Singleton
public class NewsApiService {
    private static final String apiKey = "5b4e00f3046843138d8368211777a4f2";
    private static final String sourcesUrl = "http://newsapi.org/v1/sources?";
    private static final String articlesUrl = "http://newsapi.org/v1/articles?";

/*    static {
        String certificatesTrustStorePath = "/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/security/cacerts";
        System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    }
*/
    
    @Resource
    private TimerService timer;
    
    
    @EJB
    private NewsSourceService sourcesService;

    @EJB
    private NewsArticleService articlesService;

    @EJB
    private NewsAuthorService authorsService;

    public void setSourcesService(NewsSourceService sourcesService) {
        this.sourcesService = sourcesService;
    }

    public void setArticlesService(NewsArticleService articlesService) {
        this.articlesService = articlesService;
    }

    public void setAuthorsService(NewsAuthorService authorsService) {
        this.authorsService = authorsService;
    }

    @Schedule(hour = "*", minute = "*/10")
    public void loadData() {
        System.out.println("===> Loading data from newsapi.org...");
        try {
            String query = createSourceQuery("", "", "");
            System.out.println("===> Query for newapi.org: " + query);

            JSONObject jsonObject     = makeApiCall(query);
            JSONArray allSourcesArray = jsonObject.getJSONArray("sources");
            for(int i = 0; i < allSourcesArray.length(); i++) {
                JSONObject obj = allSourcesArray.getJSONObject(i);
                
                NewsSource source = createSource(obj);
                loadArticles(source);
                sourcesService.save(source);
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("<=== Data loaded.");
    }

    private void loadArticles(NewsSource source) {
        try {
            String query = createArticlesQuery(source.getSourceId(), "");
            
            //sourcesService.save(source);
            //source = sourcesService.findSourceBySourceId(source.getSourceId());
            JSONObject jsonObject = makeApiCall(query);
            
            JSONArray allArticlesArray = jsonObject.getJSONArray("articles");
            
            for(int i = 0; i < allArticlesArray.length(); i++) {
                JSONObject obj = allArticlesArray.getJSONObject(i);
                
                NewsArticle article = new NewsArticle();
                NewsAuthor author = null;
                if(!obj.isNull("title")) {
                    article.setTitle(normalize(obj.getString("title")));
                }
                if(!obj.isNull("description")) {
                    article.setDescription(obj.getString("description"));
                }
                if(!obj.isNull("url")) {
                    article.setUrl(obj.getString("url"));
                }
                if(!obj.isNull("urlToImage")) {
                    article.setImageUrl(obj.getString("urlToImage"));
                }
                if(!obj.isNull("publishedAt")) {
                    article.setPublishedAt(obj.getString("publishedAt"));
                }
                
                //articlesService.save(article);
                //article = articlesService.findArticleByTitle(article.getTitle());
                String authorName = null;
                if(!obj.isNull("author")) {
                    authorName = obj.getString("author");
                    author = new NewsAuthor(authorName.trim());
                    author.addArticle(article);
                    source.addCorrespondent(author);
                }
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private String createSourceQuery(String category, String language2Letter, String country) {
        String query = sourcesUrl;
        
        query += ("category=" + category);
        if(language2Letter != null) {
            query += ("&language=" + language2Letter);
        }
        if(country != null) {
            query += ("&country=" + country);
        }
        query += ("&apiKey=" + apiKey);
        return query;
    }

    private String createArticlesQuery(String sourceId, String sortBy) {
        String query = articlesUrl;
        
        query += ("source=" + sourceId);
        if(sortBy != null) {
            query += ("&sortBy=" + sortBy);
        }
        query += ("&apiKey=" + apiKey);
        return query;
    }
    
    
    private JSONObject makeApiCall(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openConnection().getInputStream();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          JSONObject json = new JSONObject(jsonText);
          return json;
        } finally {
          is.close();
        }
    }
    
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
    }

    private  NewsSource createSource(JSONObject obj) {
        NewsSource source = new NewsSource();
        
        if(!obj.isNull("id")) {
            source.setSourceId(obj.getString("id"));
        }
        if(!obj.isNull("name")) {
            source.setName(obj.getString("name"));
        }
        if(!obj.isNull("description")) {
            source.setDescription(obj.getString("description"));
        }
        if(!obj.isNull("url")) {
            source.setUrl(obj.getString("url"));
        }
        if(!obj.isNull("category")) {
            source.setCategory(obj.getString("category"));
        }
        if(!obj.isNull("country")) {
            source.setCountry(obj.getString("country"));
        }
        if(!obj.isNull("language")) {
            source.setLanguage(obj.getString("language"));
        }
        
        return source;
    }

    private String normalize(String string) {
        return string.replace("\"", "'");
    }

}
