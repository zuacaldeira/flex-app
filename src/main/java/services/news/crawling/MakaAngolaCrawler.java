/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news.crawling;

import db.news.NewsSource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author zua
 */
@Singleton
public class MakaAngolaCrawler extends FlexNewsCrawler {

    public MakaAngolaCrawler() {
        super();
    }
    
    private String getUrl() {
        return "https://www.makaangola.org";
    }
    
    @Schedule(hour = "*", minute = "*/10")
    public void crawl() {
        crawlWebsite(getUrl(), getMySource());
    }



    private String normalizeTime(String time) {
        String result = normalizeTime("yyyy-MM-dd'T'HH:mm:ssXXX", time);
        if(result != null) {
            return result;
        }
        return null;
    }

    @Override
    public NewsSource getMySource() {
        String sourceId = "maka-angola";
        String name = "Maka Angola";
        String description = "Em defesa da democracia, contra a corrupção";
        String url = getUrl();
        String category = "política";
        String language = "pt";
        String country = "ao";
        String logoUrl = "https://www.makaangola.org/wp-content/uploads/2016/05/logo.png";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(logoUrl);
        
        return source;
    }

    @Override
    protected Elements getArticles(Document document) {
        return document.select("article");
    }

    @Override
    protected String getUrlValue(Element article) {
        Element link = article.select("a").first();
        if (link != null) {
            return link.absUrl("href");
        }
        return null;    
    }

    @Override
    protected String getTitleValue(Document document) {
        Elements elements = document.select(".post-title");
        return elements.text().trim();
    }

    @Override
    protected String getImageUrlValue(Document document) {
        Element image = document.select("section.primary > article > img").first();
        if (image != null) {
            return image.attr("src");
        }
        return null;
    }

    @Override
    protected String getContentValue(Document document) {
        Element content = document.select("div.entry.clearfix > p:nth-child(1)").first();
        if (content != null) {
            return content.text();
        }
        return null;
    }

    @Override
    protected String getAuthorsValue(Document document) {
        Elements authors = document.select(".author");
        if(!authors.isEmpty() &&  !authors.text().isEmpty()) {
            return authors.text();
        }
        return "Maka Angola";
    }

    @Override
    protected String getTimeValue(Document document) {
        Element time = document.select("time").first();
        if (time != null) {
            String normalizedTime = normalizeTime(time.attr("datetime")); 
            return normalizedTime;
        }
        return null;
    }
}
