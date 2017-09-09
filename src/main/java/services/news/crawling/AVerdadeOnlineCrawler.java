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
public class AVerdadeOnlineCrawler extends FlexNewsCrawler {

    public AVerdadeOnlineCrawler() {
        super();
    }


    private String getUrl() {
        return "http://www.verdade.co.mz";
    }
    
    @Schedule(hour = "*", minute = "4/5", persistent = false)
    public void crawlSet1() {
        crawlWebsite(getUrl(), getMySource());
        //crawlWebsite(URL_HOME + "/destaques");
        /*crawlWebsite(URL_HOME + "/destaques/africa");
        crawlWebsite(URL_HOME + "/destaques/democracia");
        crawlWebsite(URL_HOME + "/destaques/economia");
        crawlWebsite(URL_HOME + "/destaques/global-voices");
        crawlWebsite(URL_HOME + "/destaques/internacional");
        crawlWebsite(URL_HOME + "/destaques/nacional");
        crawlWebsite(URL_HOME + "/destaques/tecnologias");
        crawlWebsite(URL_HOME + "/destaques/tema-de-fundo");*/
    }
    
    @Override
    public NewsSource getMySource() {
        String sourceId = "verdade-online";
        String name = "@Verdade Online";
        String description = "";
        String url = getUrl();
        String category = "geral";
        String language = "pt";
        String country = "mz";
        String logoUrl = "http://www.verdade.co.mz/images/favicon.ico";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(logoUrl);
        
        return source;
    }

    
    /*
    ""
    */
    @Override
    protected Elements getArticles(Document document) {
        Elements articles = document.select("table + table");
        return articles;
    }

    @Override
    protected String getUrlValue(Element article) {
        Elements links = article.select("table > tbody > tr > td > a");
        for(Element link: links) {
            String href = link.absUrl("href");
            if(href != null && !href.isEmpty() && href.startsWith(getUrl())) {
                return href;
            }
        }
        return null;
    }

    /*
    "";
    */
    @Override
    protected String getTitleValue(Document document) {
        Elements links = document.select("td.contentheading");
        for(Element link: links) {
            String text = link.text();
            if(text != null && !text.isEmpty()) {
                return text;
            }
        }
        return null;
    }

    /*
    "#main_body > div > table > tbody > tr > td > p > img[src]"
    */
    @Override
    protected String getImageUrlValue(Document document) {
        Elements images = document.select("p > img");
        for(Element image: images) {
            String src = image.attr("src");
            if(src != null && !src.isEmpty()) {
                return getFullImageUrl(src);
            }
        }
        return null;
    }

    @Override
    protected String getFullImageUrl(String src) {
        if(!src.startsWith(getUrl())) {
            return getUrl() + src;
        }
        return src;
    }


    
    /*
    "main > article > p.info-autor"
    */
    @Override
    protected String getAuthorsValue(Document document) {
        String result = getMySource().getName();
        Elements elements = document.select("td > span.small > a");
        String text = elements.text().trim();
        if(text != null && !text.isEmpty()) {
            result = text;
            if(result.contains("|")) {
                int i = result.indexOf('|');
                result = result.substring(0, i);
            }
        }
        return result;
    }

    /*
     "#main_body > div > table:nth-child(4) > tbody > tr:nth-child(3) > td > p:nth-child(3)"
    */
    @Override
    protected String getContentValue(Document document) {
        Elements elements = document.select("tbody > tr > td > p");
        Element first = elements.first();
        if(first != null) {
            String text = first .text().trim();
            if(text != null && !text.isEmpty()) {
                return text;
            }
        }
        return null;
    }

    /*
    "main > article > p > time"
    */
    @Override
    protected String getTimeValue(Document document) {
        Elements elements = document.select("td > span.small");
        String text = elements.first().ownText().trim();
        if(text != null && !text.isEmpty()) {
            String[] parts = text.split(" ");
            int k = parts.length;
            if(k >= 3) {
                String result = parts[k-3] + " " + parts[k-2] + " " + parts[k-1];
                return normalizeTime(result);
            }
        }
        return null;
    }
    
    private String normalizeTime(String time) {
        try {
            //System.out.println("###### Normalize " + time);
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", new Locale(getMySource().getLanguage()));
            Date d = format.parse(time);
            Date now = new Date();
            d.setHours(now.getHours());
            d.setMinutes(now.getMinutes());
            d.setSeconds(now.getSeconds());
            //System.out.println("###### Parsed " + d);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", new Locale(getMySource().getLanguage()));
            String result = format2.format(d);
            //System.out.println("###### Reformated as " + result);
            //Date d2 = format2.parse(result);
            //System.out.println("###### Proof date " + d2);
            return result;
        } catch(ParseException px) {
            System.out.println("###### Couldn't parse " + time);
            return null;
        }
    }


}
