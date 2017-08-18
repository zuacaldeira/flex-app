/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author zua
 */
@Singleton
public class FlexNewsCrawler {
    
    @EJB
    private NewsArticleService newsService;
    @EJB
    private NewsAuthorService authorsService;
    @EJB
    private NewsSourceService sourcesService;
    
    //@Schedule(hour = "*")
    public void crawlEverything() {
        System.out.println("[CRAWLER] START");
        Iterable<NewsArticle> articles = loadArticles();
        articles.forEach(article -> {
            System.out.println("[CRAWLER]\tCrawling " + article.getUrl());
            try {
                crawlUrl(article.getUrl());
            } catch(Exception e) {
                // DO nothing
            }
        });
        System.out.println("[CRAWLER] END");
    }
    
    @Schedule(hour = "*", minute="*/10")
    public void crawlMakaAngola() {
        System.out.println("[CRAWLER MAKA-ANGOLA] START");
        Document document = openDocument("https://www.makaangola.org");
        crawlDocument(document);
        System.out.println("[CRAWLER MAKA-ANGOLA] END");        
    }
    
    private void crawlDocument(Document document) {
        NewsSource source = crawlNewsSource(document);
        crawlUltimasNoticias(document, source);
        crawlSection(document, source, "CATEGORIAS");
        crawlSection(document, source, "ARQUIVO");
        crawlSection(document, source, "ETIQUETAS");
    }
    
    /**
     * Connects to the web address.
     * @param url   A web address url, starting with http(s).
     * @return      The top document representing the content of web address.
     */
    private Document openDocument(String url) {
        try{
            return Jsoup.connect(url).get();
        } catch(Exception e) {
            throw new NewsServiceException(e);
        }
    }
    
    private void crawlUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            System.out.println("[CRAWLER]\t\tTitle: " + doc.title());
            doc.getAllElements().forEach(element -> {
                Map<String, String> dataset = element.dataset();
                System.out.printf("[CRAWLER]\t\tElement name = %s, dataset size = %d\n", element.nodeName() ,dataset.size());
            });
        } catch (Exception e) {
            throw new NewsCrawlerException(e);
        }
    }

    private Iterable<NewsArticle> loadArticles() {
        if(newsService != null) {
            return newsService.findAll();
        } else {
            return new LinkedList<>();
        }
    }

    private Date getDate(String dateString) {
        try {
            Date d = Date.from(Instant.parse(dateString.replace("+00:00", ".00Z")));
            return d;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return new Date();
        }
    }

    private NewsSource crawlNewsSource(Document document) {
        String sourceId = "makaangola";
        String name = "Maka Angola";
        String description = "Em defesa da democracia, contra a corrupção";
        String url = "https://www.makaangola.org";
        String category = "política";
        String language = "pt";
        String country = "ao";
        
        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        
        String logoUrl = null;
        if(!document.select("#logo").isEmpty()) {
            logoUrl = document.select("#logo").first().absUrl("href");
            System.out.println("Logo url: " + logoUrl);
            source.setLogoUrl(logoUrl);
        }
        
        NewsSource dbSource = sourcesService.find(source);
        
        // TODO: Get logo image
        
        if(dbSource != null) {
            return dbSource;
        }
        else{
            return source;
        }
    }

    private void crawlUltimasNoticias(Document document, final NewsSource source) {
        Elements articles = document.select("article");
        articles.forEach(article -> {
            try {
                
                System.out.println("[CRAWLER MAKA-ANGOLA] element: " + article.cssSelector());
                System.out.println("[CRAWLER MAKA-ANGOLA] article text : " + article.text());
                
                Element image = article.select("img").first();
                System.out.println("[CRAWLER MAKA-ANGOLA] article img : " + image.attr("src"));
                
                Element link = article.select("a").first();
                System.out.println("[CRAWLER MAKA-ANGOLA] article url: " + link.absUrl("href"));

                Element title = article.select("h2").first();
                System.out.println("[CRAWLER MAKA-ANGOLA] article title: " + title.text());

                Element time = article.select("time").first();
                System.out.println("[CRAWLER MAKA-ANGOLA] article time: " + time.attr("datetime"));
                
                Element content = article.select(".entry").first();
                System.out.println("[CRAWLER MAKA-ANGOLA] article content: " + content.text());
                
                NewsArticle newsArticle = new NewsArticle(title.text(), content.text(), link.absUrl("href"), image.attr("src"), getDate(time.attr("datetime")));
                newsArticle.setSourceId(source.getSourceId());

                String name = "TODO";
                NewsAuthor newsAuthor = new NewsAuthor(name);
                NewsAuthor dbAuthor = authorsService.find(newsAuthor);
                NewsAuthor author = (dbAuthor == null)? newsAuthor: dbAuthor;
                author.addArticle(newsArticle);
                source.addCorrespondent(author);
                
                try {
                    newsService.save(newsArticle);                   
                } catch(NewsServiceException nsx) {
                    newsService.update(newsArticle);
                }
            } catch(Exception e) {
                // DO nothing and continue iterating
                System.err.println(e.getMessage());
            }
        });
    }

    private void crawlSection(Document document, NewsSource source, String sectionName) {
        Elements section = document.select(sectionName);
        System.out.println("[CRAWLER MAKA-ANGOLA SECTION] Crawling " + section.size() + " sections");
    }
    
    
}
