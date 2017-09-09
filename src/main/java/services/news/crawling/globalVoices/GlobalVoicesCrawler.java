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
public class GlobalVoicesCrawler extends GlobalVoicesAbstractCrawler {

    public GlobalVoicesCrawler() {
    }

    @Schedule(hour = "*", minute = "*/17", persistent = false)
    public void crawlSet1() {
        crawlWebsite(getUrl("am"), getMySource("am"));
        crawlWebsite(getUrl("ar"), getMySource("ar"));
        crawlWebsite(getUrl("aym"), getMySource("aym"));
        crawlWebsite(getUrl("bg"), getMySource("bg"));
        crawlWebsite(getUrl("bn"), getMySource("bn"));
        crawlWebsite(getUrl("ca"), getMySource("ca"));
        crawlWebsite(getUrl("cs"), getMySource("cs"));
        crawlWebsite(getUrl("da"), getMySource("da"));
        crawlWebsite(getUrl("de"), getMySource("de"));
        crawlWebsite(getUrl("el"), getMySource("el"));
        crawlWebsite(getUrl("en"), getMySource("en"));
        crawlWebsite(getUrl("eo"), getMySource("eo"));
        crawlWebsite(getUrl("es"), getMySource("es"));
        crawlWebsite(getUrl("fa"), getMySource("fa"));
        crawlWebsite(getUrl("fil"), getMySource("fil"));
        crawlWebsite(getUrl("fr"), getMySource("fr"));
        crawlWebsite(getUrl("he"), getMySource("he"));
        crawlWebsite(getUrl("hi"), getMySource("hi"));
        crawlWebsite(getUrl("hu"), getMySource("hu"));
        crawlWebsite(getUrl("id"), getMySource("id"));
        crawlWebsite(getUrl("it"), getMySource("it"));
        crawlWebsite(getUrl("jp"), getMySource("jp"));
        crawlWebsite(getUrl("km"), getMySource("km"));
        crawlWebsite(getUrl("ko"), getMySource("ko"));
        crawlWebsite(getUrl("mg"), getMySource("mg"));
        crawlWebsite(getUrl("mk"), getMySource("mk"));
        //crawlWebsite(getUrl("my"), getMySource("my"));
        crawlWebsite(getUrl("ne"), getMySource("ne"));
        crawlWebsite(getUrl("or"), getMySource("or"));
        crawlWebsite(getUrl("pa"), getMySource("pa"));
        crawlWebsite(getUrl("pl"), getMySource("pl"));
        crawlWebsite(getUrl("ps"), getMySource("ps"));
        crawlWebsite(getUrl("pt"), getMySource("pt"));
        crawlWebsite(getUrl("ro"), getMySource("ro"));
        crawlWebsite(getUrl("ru"), getMySource("ru"));
        crawlWebsite(getUrl("sq"), getMySource("sq"));
        crawlWebsite(getUrl("sr"), getMySource("sr"));
        crawlWebsite(getUrl("sv"), getMySource("sv"));
        crawlWebsite(getUrl("sw"), getMySource("sw"));
        crawlWebsite(getUrl("tet"), getMySource("tet"));
        crawlWebsite(getUrl("tr"), getMySource("tr"));
        crawlWebsite(getUrl("ur"), getMySource("ur"));
        crawlWebsite(getUrl("zhs"), getMySource("zhs"));
        crawlWebsite(getUrl("zht"), getMySource("zht"));
    }
    
    @Override
    public NewsSource getMySource() {
        String sourceId = "global-voices";
        String name = "Global Voices";
        String description = "";
        String url = "https://globalvoices.org";
        String category = "geral";
        String language = "en";
        String country = "uk";
        String logoUrl = "https://s3.amazonaws.com/static.globalvoices/img/tmpl/gv-logo-oneline-smallicon-600.png";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(logoUrl);
        
        return source;
    }

    
    private String getUrl(String language) {
        return "http://" + language + ".globalvoices.org";
    }
    
    private NewsSource getMySource(String language) {
        NewsSource source = getMySource();
        source.setSourceId(source.getSourceId() + "-" + language);
        source.setName(source.getName() + " (" + language + ")");
        source.setUrl(getUrl(language));
        source.setDescription(source.getDescription());
        source.setLanguage(language);
        return source;
    }
}
