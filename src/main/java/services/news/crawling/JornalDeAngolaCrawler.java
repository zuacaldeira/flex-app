/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news.crawling;

import db.news.NewsSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class JornalDeAngolaCrawler extends FlexNewsCrawler {

    public JornalDeAngolaCrawler() {
        super("http://jornaldeangola.sapo.ao");
    }

    
    @Schedule(hour = "*", minute = "2/10", persistent = false)
    public void crawl() {
        //FlexLogger.getInstance().on();
        crawlWebsite(getUrl());
        crawlWebsite(getUrl() + "/cultura");
        crawlWebsite(getUrl() + "/desporto");
        /*crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/economia");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/gente");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/mundo");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/opiniao");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/politica");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/provincias");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/reportagem");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/sociedade");*/
    }
    
    @Override
    public NewsSource getMySource() {
        String sourceId = "jornal-de-angola";
        String name = "Jornal de Angola";
        String description = "";
        String url = getUrl();
        String category = "geral";
        String language = "pt";
        String country = "ao";
        String logoUrl = "http://imgs.sapo.pt/jornaldeangola/2016/img/jornal-de-angola-2017.png";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(logoUrl);
        
        return source;
    }

    
    
    private String getArticleSelector() {
        return "article";
    }
    
    private String getUrlSelector() {
        return "article > a[href]";
    }  
    
    private String getTitleSelector() {
        return "main  article  h1";
    }
    
    private String getImageSelector() {
        return "main article img";
    }

    

    private String getDescriptionSelector() {
        return "main article p.lead ~ p";
    }
    
    
    private String getTimeSelector() {
        return "main  article  p > time.date-time";
    }
    
    private String getAuthorsSelector() {
        return "main article p.info-autor";
    }

    

    @Override
    protected String getTimeValue(Document document) {
        if(document != null) {
            Element first = document.select(getTimeSelector()).first();
            if(first!= null) {
                String time = first.attr("datetime") ;
                return normalizeTime("yyyy-MM-dd HH:mm:ss", time);
            }
        }
        return null;
    }
    

    @Override
    protected String getUrlValue(Element article) {
        Elements urls = article.select(getUrlSelector());
        for(Element element: urls) {
            String value = element.absUrl("href");
            if(value.startsWith("http://jornaldeangola.sapo.ao")) {
                return value;
            }
        }
        return null;
    }

    @Override
    protected String getTitleValue(Document document) {
        if(document != null) {
            Element first = document.select(getTitleSelector()).first();
            if(first != null) {
                String text = first.text();
                if(!text.isEmpty()) {
                    return text;
                }
            }
        }
        return null;
    }

    @Override
    protected String getImageUrlValue(Document document) {
        if(document != null) {
            Element first = document.select(getImageSelector()).first();
            if(first != null) {
                String value = first.attr("src");
                if(value != null && value.startsWith("http://imgs")) {
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    protected String getContentValue(Document document) {
        if(document != null) {
            Element first = document.select(getDescriptionSelector()).first();
            if(first != null) {
                String text = first.text().trim();
                if(text != null && !text.isEmpty()) {
                    return text;
                }
            }
        }
        return null;
    }

    @Override
    protected String getAuthorsValue(Document document) {
        String result = getMySource().getName();
        if(document != null) {
            Elements elements = document.select(getAuthorsSelector());
            String text = elements.text().trim();
            if(text != null && !text.isEmpty()) {
                if(text.contains("|")) {
                    int i = text.indexOf('|');
                    result = text.substring(0, i);
                }
            }
        }        
        return result;
    }

    @Override
    protected Elements getArticles(Document document) {
        return document.select(getArticleSelector());
    }
}
