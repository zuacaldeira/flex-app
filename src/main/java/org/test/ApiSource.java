package org.test;

/**
 * Created by zua on 12/04/17.
 */
public class ApiSource {
    private final String id;
    private final String name;
    private final String description;
    private final String url;
    private final String category;
    private final String language;
    private final String country;

    public ApiSource(String id, String name, String description, String url, String category, String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name;
    }

}
