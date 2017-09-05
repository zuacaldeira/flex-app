package services.news;

import db.news.NewsArticle;
import db.news.NewsSource;
import db.news.NewsAuthor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.FlexUtils;

/**
 * Created by zua on 15/04/17.
 */
@Singleton
public class NewsApiService {
    private static final String apiKey = "5b4e00f3046843138d8368211777a4f2";
    private static final String sourcesUrl = "http://newsapi.org/v1/sources?";
    private static final String articlesUrl = "http://newsapi.org/v1/articles?";

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

    @Schedule(hour = "*", minute = "*/15", persistent = false)
    public void loadData() {
        try {
            System.out.println("[LOADER] START: Loading data from newsapi.org...");
            processSources(makeApiCall(createSourceQuery("", "", "")));
            System.out.println("[LOADER] END: Data loaded.");
        } catch (Exception e) {
            throw new NewsServiceException(e);
        }
    }
    
    private void processSources(JSONObject jsonObject) {
        JSONArray allSourcesArray = jsonObject.getJSONArray("sources");
        for(int i = 0; i < allSourcesArray.length(); i++) {
            try {
                processSource(createSource(allSourcesArray.getJSONObject(i)));
            } catch (Exception e) {
                //System.err.println(e.getMessage());
                //throw new NewsServiceException(e);
            }
        }
    }
    
    
    private void processSource(NewsSource source) {
        try {
            System.out.println("[LOADER] Loading articles from " + source.getName());
            processArticles(source, makeApiCall(createArticlesQuery(source.getSourceId(), "")));
         } catch (Exception e) {
            throw new NewsServiceException(e);
        }
    }
    
    private void processArticles(NewsSource source, JSONObject jsonObject) {
           
        JSONArray allArticlesArray = jsonObject.getJSONArray("articles");

        for(int i = 0; i < allArticlesArray.length(); i++) {
            try {
                processArticle(source, allArticlesArray.getJSONObject(i));
            } catch(Exception e) {
                //System.err.println(e.getMessage());
                //throw new NewsServiceException(e);
            }
        }
    }
    
    private String normalizeTime(String dateString, String language) {
        String result = normalizeTime("yyyy-MM-dd HH:mm:ss", dateString, language);
        if(result == null) {
            result = normalizeTime("yyyy-MM-dd HH:mm:ssZ", dateString, language);
        }
        if(result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss", dateString, language);
        }
        if(result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss'Z'", dateString, language);
        }
        if(result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", dateString, language);
        }
        if(result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", dateString, language);
        }
        if(result == null) {
            result = normalizeTime("yyyy-MM-dd'T'HH:mm:ss.SSSZ", dateString, language);
        }
        
        //System.out.println("Converted " + dateString + " into " + result);
        
        if(result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    
    private void processArticle(NewsSource source, JSONObject obj) {
        try {
            NewsArticle article = new NewsArticle();
            article.setSourceId(source.getSourceId());
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
                String normalizedTime = normalizeTime(obj.getString("publishedAt"), source.getLanguage());
                Date date = FlexUtils.getInstance().getDate2(normalizedTime, source.getLanguage());
                article.setPublishedAt(date);
            } else {
                article.setPublishedAt(new Date());
            }

            String authorName = null;
            if(!obj.isNull("author")) {
                authorName = obj.getString("author").trim();
                Set<String> authorsNames = FlexUtils.getInstance().extractAuthors(authorName);
                Set<NewsAuthor> authors = new HashSet<>();
                
                authorsNames.forEach(name -> {
                    NewsAuthor dbAuthor = authorsService.findAuthorByName(name);
                    NewsAuthor author = (dbAuthor == null) ? new NewsAuthor(name) : dbAuthor;
                    authors.add(author);
                });

                article.setAuthors(authors);
                source.setCorrespondents(authors);
                
                articlesService.save(article);
                System.out.println("[LOADER]\tStored new article " + article.getTitle());
            }
        } catch (Exception ex) {
            throw new NewsServiceException(ex);
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
        
        NewsSource dbSource = sourcesService.find(source);
        if(dbSource != null) {
            return dbSource;
        }
        else{
            return source;
        }
    }

    private String normalize(String string) {
        return string.replace("\"", "'");
    }

    private String normalizeTime(String datePattern, String dateString, String language) {
        try {
            SimpleDateFormat formatIn = new SimpleDateFormat(datePattern, new Locale(language));
            Date date = formatIn.parse(dateString);
            
            SimpleDateFormat formatOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", new Locale(language));
            return formatOut.format(date);
        } catch(ParseException px) {
            return null;
        }
    }

}
