package org.test;


import elemental.json.JsonArray;
import elemental.json.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zua on 11/04/17.
 */
public class NewsApiOrg {

    private static final String apiKey = "5b4e00f3046843138d8368211777a4f2";
    private static String sourcesUrl = "https://newsapi.org/v1/sources?";
    private static String articlesUrl = "https://newsapi.org/v1/articles?";

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

    public static ApiSources GET_ApiSources() {
        String query          = NewsApiOrg.createSourceQuery(null, "en", null);
        System.out.println("Query is = " + query);

        String jsonString     = makeApiCall(query);
        ApiSources apiSources = new ApiSources();

        try {
            elemental.json.JsonObject jsonObject = elemental.json.Json.parse(jsonString);
            JsonArray allSourcesArray = jsonObject.getArray("sources");

            for(int i = 0; i < allSourcesArray.length(); i++) {
                JsonObject obj = allSourcesArray.getObject(i);

                String id = obj.getString("id");
                String name = obj.getString("name");
                String description = obj.getString("description");
                String url = obj.getString("url");
                String category = obj.getString("category");
                String language = obj.getString("language");
                String country = obj.getString("country");

                apiSources.addSource(id, name, description, url, category, language, country);
            }
            return apiSources;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static ApiArticles GET_Articles(ApiSource source) {
        String query = NewsApiOrg.createArticlesQuery(source.getId(), "latest");
        System.out.println("Query is = " + query);

        String jsonString     = makeApiCall(query);

        ApiArticles apiArticles = new ApiArticles();
        try {
            elemental.json.JsonObject jsonObject = elemental.json.Json.parse(jsonString);
            JsonArray allArticlesArray = jsonObject.getArray("articles");

            for(int i = 0; i < allArticlesArray.length(); i++) {
                JsonObject obj = allArticlesArray.getObject(i);

                String author = obj.getString("author");
                String title = obj.getString("title");
                String description = obj.getString("description");
                String url = obj.getString("url");
                String imageUrl = obj.getString("urlToImage");
                String publishedAt = obj.getString("publishedAt");

                apiArticles.addArticle(source.getId(), author, title, description, url, imageUrl, publishedAt);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return apiArticles;
    }

    private static String createSourceQuery(String category, String language2Letter, String country) {
        String query = sourcesUrl;
        return query;
    }

    private static String createArticlesQuery(String sourceId, String sortBy) {
        String query = articlesUrl;
        query += ("source=" + sourceId);
        query += ("&apiKey=" + apiKey);
        return query;
    }

    private static String makeApiCall(String url) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            //request.addHeader("x-api-key", apiKey);
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            return json;
        } catch (IOException ex) {}
        return null;
    }


}
