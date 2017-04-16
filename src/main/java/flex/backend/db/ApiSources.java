package flex.backend.db;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zua on 12/04/17.
 */
public class ApiSources {

    private Map<String, ApiSource> sourcesMap;

    public ApiSources() {
        sourcesMap = new TreeMap<>();
    }

    public void addSource(String id, String name, String description, String url, String category, String language, String country) {
        sourcesMap.put(id, new ApiSource(id, name, description, url, category, language, country));
    }

    public ApiSource getSource(String s) {
        return sourcesMap.get(s);
    }

    public Collection<ApiSource> getSources() {
        return sourcesMap.values();
    }
}
