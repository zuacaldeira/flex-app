package flex.backend.news;



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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zua on 11/04/17.
 */
public class NewsApiOrg {

    private static final String apiKey = "5b4e00f3046843138d8368211777a4f2";
    private static String sourcesUrl = "http://newsapi.org/v1/sources?";
    private static String articlesUrl = "http://newsapi.org/v1/articles?";

    static {
        String certificatesTrustStorePath = "/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/security/cacerts";
        System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    }
    private static Map<String, String> getSourceParams() {
        Map<String, String> params = new HashMap<>();

        params.put("category",
                "(optional) - The category you would like to get sources for.\n" +
                "Possible options: business, entertainment, gaming, general, music, politics, " +
                "science-and-nature, sport, technology.\n" +
                "Default: empty (all sources returned)");

        params.put("language", "(optional) - The 2-letter ISO-639-1 code of the language you would " +
                "like to get sources for.\n" +
                "Possible options: en, de, fr.\n" +
                "Default: empty (all sources returned)");

        params.put("country", "(optional) - The 2-letter ISO 3166-1 code of the country you would " +
                "like to get sources for.\n" +
                "Possible options: au, de, gb, in, it, us.\n" +
                "Default: empty (all sources returned)");

        return params;
    }

    private static Map<String, String> getArticlesParams() {
        Map<String, String> params = new HashMap<>();
        params.put("source",
                "(required) - The identifer for the news source or blog you want headlines from. Use the /sources " +
                        "endpoint to locate this or use the sources index.");

        params.put("apiKey", "(required) - Your API key. Alternatively you can provide this via the X-Api-Key HTTP " +
                "header.\n");

        params.put("sortBy", "(optional) - Specify which type of list you want. The possible options are top, latest and " +
                "popular. Note: not all options are available for all sources. Default: top.\n" +
                "top\tRequests a list of the source's headlines sorted in the order they appear on its homepage.\n" +
                "latest\tRequests a list of the source's headlines sorted in chronological order, newest first.\n" +
                "popular\tRequests a list of the source's current most popular or currently trending headlines.");

        return params;
    }
    
    public static Map<NewsSource, Map<NewsAuthor, Collection<NewsArticle>>> loadData() {
        System.out.println("===> Loading data from newsapi.org...");
        Map<NewsSource, Map<NewsAuthor, Collection<NewsArticle>>> data = new HashMap<>();
        try {
            String query = NewsApiOrg.createSourceQuery("", "", "");
            System.out.println("===> Query for newapi.org: " + query);

            JSONObject jsonObject     = makeApiCall(query);
            JSONArray allSourcesArray = jsonObject.getJSONArray("sources");
            for(int i = 0; i < allSourcesArray.length(); i++) {
                JSONObject obj = allSourcesArray.getJSONObject(i);
                
                NewsSource source = createSource(obj);                
                System.out.println("===> Source found: " + source);
                
                ApiArticles articles = loadArticles(source);
                System.out.println("===> Articles found: " + articles.getArticlesMap().size());

                data.put(source, articles.getArticlesMap());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        
        System.out.println("<=== Data loaded.");
        return data;
    }

    private static ApiArticles loadArticles(NewsSource source) {
        System.out.println("LOADING ARTICLES FOR SOURCE " + source);
        
        String query = NewsApiOrg.createArticlesQuery(source.getSourceId(), "");

        ApiArticles apiArticles = new ApiArticles(source);

        try {
            JSONObject jsonObject = makeApiCall(query);

            JSONArray allArticlesArray = jsonObject.getJSONArray("articles");

            for(int i = 0; i < allArticlesArray.length(); i++) {
                JSONObject obj = allArticlesArray.getJSONObject(i);
                
                NewsArticle article = new NewsArticle();
                Set<NewsAuthor> authors = null;
                
                if(!obj.isNull("author")) {
                    System.out.println(obj.getString("author").toUpperCase());
                    authors = extractAuthors(obj.getString("author"));
                }
                if(!obj.isNull("title")) {
                    article.setTitle(obj.getString("title"));
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

                apiArticles.addArticle(article, authors);
            }
        } catch (Exception e) {
            System.err.println("EXCEPTON at LOAD ARTICLES: " + e.getMessage());
        }
        return apiArticles;
    }
    
    
    private static Set<NewsAuthor> extractAuthors(String value) {
        Set<NewsAuthor> authors = new HashSet<>();

        String[] parts = value.split(",");
        Set<String> allParts = new HashSet<>(Arrays.asList(parts));
        for(String part: allParts) {
            System.out.println("Processing author... " + part.trim());
            authors.add(getSingleActor(part.trim()));
        }
        
        return authors;
    }

    private static String createSourceQuery(String category, String language2Letter, String country) {
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

    private static String createArticlesQuery(String sourceId, String sortBy) {
        String query = articlesUrl;
        
        query += ("source=" + sourceId);
        if(sortBy != null) {
            query += ("&sortBy=" + sortBy);
        }
        query += ("&apiKey=" + apiKey);
        return query;
    }
    
    
    private static JSONObject makeApiCall(String url) throws IOException, JSONException {
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
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
    }

    private static NewsSource createSource(JSONObject obj) {
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

    private static NewsAuthor getSingleActor(String value) {
        NewsAuthor author = new NewsAuthor();
        if(value.startsWith("http")) {
            author.setUrl(value);
        }
        else {
            author.setName(value);
        }
        return author;
    }
}
