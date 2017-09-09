/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news.crawling.globalVoices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import services.news.crawling.FlexNewsCrawler;

/**
 *
 * @author zua
 */
public abstract class GlobalVoicesAbstractCrawler extends FlexNewsCrawler {

    public GlobalVoicesAbstractCrawler() {
        super();
    }
    
    
    @Override
    protected Elements getArticles(Document document) {
        Elements body = document.select("div.main");
        return body.select("div.post-summary-content");
    }
    
    @Override
    protected String getUrlValue(Element article) {
        return article.select("a").first().absUrl("href");
    }

    @Override
    protected String getTitleValue(Document document) {
        return document.select("div.post-header a").text();
    }

    @Override
    protected String getImageUrlValue(Document document) {
        String src = document.select("div > a > img").attr("src");
        if(src != null) {
            //style = style.replace("background-image: url(", "");
            //style = style.replace(")", "");
            return src;
        }
        return null;
    }

    @Override
    protected String getContentValue(Document document) {
        Elements paragraphs = document.select("div#single.entry > p");
        return paragraphs.first().text();
    }

    @Override
    protected String getAuthorsValue(Document document) {
        if(document != null) {
            Elements elements = document.select("#sidebar > div.postmeta-sidebar > div.postmeta-container.post-credit-container > div > div.author.contributor > div.contributor-name > a");
            String text = elements.text();
            if(text != null) {
                return text.trim();
            }
        }
        return null;
    }

    
    @Override
    protected String getTimeValue(Document document) {
        if(document != null) {
            try {
                Element dayElement = document.select("span.post-date").first();
                String dayString = extractDate(dayElement.select("a").attr("title")).trim();
                
                Element timeElement = dayElement.select("span.post-time").first();
                String timeString = extractTime(timeElement.text()).trim();
                
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale(getMySource().getLanguage()));
                if(dayString != null && timeString != null) {
                    Date date = format.parse(dayString + " " + timeString);
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", new Locale(getMySource().getLanguage()));
                    return format2.format(date);
                }
            } catch (ParseException ex) {
                super.getLogger().error(ex.getMessage());
            }
        }
        return null;
    }

    private String extractDate(String phrase) {
        if(phrase != null) {
            String[] parts = phrase.split(" ");
            if(parts.length > 0) {
                return parts[parts.length-1].replace('/', '-');
            }
        }
        return null;
    }

    private String extractTime(String timeString) {
        if(timeString != null) {
            String[] parts = timeString.split("GMT");
            String time = parts[0];
            if(time.length() >= 6) {
                time = time.substring(0, 5) + ":00Z";
            }
            else {
                time = "0" + time.substring(0, 4) + ":00Z";
            }
            return time;
        }
        return null;
    }
}
