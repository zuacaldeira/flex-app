package utils;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by zua on 11/04/17.
 */
public class NewsApiOrg {

    private static NewsApiOrg instance;
    
    private NewsApiOrg() {}

    public static Map<String, String> getSourceParams() {
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

    public static Map<String, String> getArticlesParams() {
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

    public static NewsApiOrg getInstance() {
        if(instance == null) {
            instance = new NewsApiOrg();
        }
        return instance;
    }
    
}
