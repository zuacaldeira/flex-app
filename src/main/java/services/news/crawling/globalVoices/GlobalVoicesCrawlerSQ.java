/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news.crawling.globalVoices;

import db.news.NewsSource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author zua
 */
@Singleton
public class GlobalVoicesCrawlerSQ extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawlerSQ() {
        super("https://sq.globalvoices.org");
    }

    @Schedule(hour = "*", minute = "*/10", persistent = false)
    public void crawlSet1() {
        crawlWebsite(getUrl());
    }
    
    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices-sq";
        String name = "Global Voices SQ";
        String description = "";
        String url = getUrl();
        String category = "geral";
        String language = "sq";
        String country = "uk";
        String logoUrl = "https://s3.amazonaws.com/static.globalvoices/img/tmpl/gv-logo-oneline-smallicon-600.png";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(logoUrl);
        
        return source;
    }

    
    
}
