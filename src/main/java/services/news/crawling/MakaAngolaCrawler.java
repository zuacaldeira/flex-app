/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news.crawling;

import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import services.news.NewsArticleService;
import services.news.NewsAuthorService;
import services.news.NewsServiceException;
import services.news.NewsSourceService;
import utils.FlexUtils;

/**
 *
 * @author zua
 */
@Singleton
public class MakaAngolaCrawler {

    @EJB
    private NewsArticleService articlesService;
    @EJB
    private NewsAuthorService authorsService;
    @EJB
    private NewsSourceService sourcesService;

    private final String MAKA_ANGOLA_URL_HOME = "https://www.makaangola.org";

    @Schedule(hour = "*", minute = "*/20")
    public void crawl() {
        System.out.println("\n[MA CRAWLER] START: " + MAKA_ANGOLA_URL_HOME);
        try {
            List<String> visited = new LinkedList<>();
            visited.add(MAKA_ANGOLA_URL_HOME);

            Document document = openDocument(MAKA_ANGOLA_URL_HOME);
            NewsSource source = getSource(document);
            crawl(document, source, visited);

        } catch (Exception e) {
            // Do nothing
            System.err.println("[MA CRAWLER] " + e.getMessage());
        }
        System.out.println("[MA CRAWLER] END: " + MAKA_ANGOLA_URL_HOME);
    }

    /**
     * Connects to the web address.
     *
     * @param url A web address url, starting with http(s).
     * @return The top document representing the content of web address.
     */
    private Document openDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (Exception e) {
            throw new NewsServiceException(e);
        }
    }

    private void crawl(Document document, NewsSource source, List<String> visited) {
        try {
            crawlArticles(document, source, visited);
        } catch(Exception e) {
            // Sliently ignore exception...
            System.err.println("Exception while crawling single article");
        }
    }

    private void crawlArticles(Document document, NewsSource source, List<String> visited) {
        Elements articles = document.select("article");
        articles.forEach(article -> {
            try {
                importArticle(article, source);
            } catch(Exception e) {}
        });
    }

    private void crawlLinks(Document document, NewsSource source, List<String> visited) {
        Elements links = document.select("a[href]");
        for(Element link :links) {
            String url = link.absUrl("href");
            if(!visited.contains(url)  && url.startsWith(MAKA_ANGOLA_URL_HOME)) {
                visited.add(url);
                crawl(openDocument(url), source, visited);
            }
        }
    }

    private NewsSource getSource(Document document) {
        String sourceId = "makaangola";
        String name = "Maka Angola";
        String description = "Em defesa da democracia, contra a corrupção";
        String url = MAKA_ANGOLA_URL_HOME;
        String category = "política";
        String language = "pt";
        String country = "ao";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        if (!document.select("#logo").isEmpty()) {
            String logoUrl = document.select("#logo").first().absUrl("src");
            System.out.println("Logo url: " + logoUrl);
            source.setLogoUrl(logoUrl);
        }

        NewsSource dbSource = sourcesService.find(source);
        return (dbSource != null) ? dbSource : source;
    }

    private void importArticle(Element article, NewsSource source) {
        //prettyPrint(article);
        String title = getTitleElement(article);
        
        System.out.println("[MA CRAWLER ] Found new article: " + title);
        
        boolean inDb =  articlesService.findArticleByTitle(title) != null;
        if (title != null && !inDb) {

            NewsArticle newsArticle = new NewsArticle();
            newsArticle.setSourceId(source.getSourceId());

            newsArticle.setTitle(title);

            Element image = article.select("img").first();
            if (image != null) {
                newsArticle.setImageUrl(image.attr("src"));
            }

            Element link = article.select("a").first();
            if (link != null) {
                newsArticle.setUrl(link.absUrl("href"));
            }

            Element time = article.select("time").first();
            if (time != null) {
                newsArticle.setPublishedAt(FlexUtils.getInstance().getDate(time.attr("datetime")));
            }

            Element content = article.select(".entry").first();
            if (content != null) {
                newsArticle.setDescription(content.text());
            }

            String name = getAuthorElement(article);
            NewsAuthor newsAuthor = new NewsAuthor(name);
            NewsAuthor dbAuthor = authorsService.find(newsAuthor);
            NewsAuthor author = (dbAuthor == null) ? newsAuthor : dbAuthor;
            author.addArticle(newsArticle);
            source.addCorrespondent(author);

            try {
                articlesService.save(newsArticle);
                System.out.println("[MA CRAWLER] \tStored new article: " + articlesService.findArticleByTitle(title).getTitle());
            } catch(Exception e) {
                System.err.println("[MA CRAWLER] \tError while storing article: " + e.getMessage());
                //e.printStackTrace();
            }
        }
    }

    private void prettyPrint(Element article) {
        System.out.println(article.className());
        article.children().forEach(el -> {
           // if(el.hasClass("post-title")) {
                System.out.println(el.className() + ": " + el.cssSelector());              
            //}
        });
    }

    private String getTitleElement(Element article) {
        for(Element el: article.children()) {
            if(el.hasClass("post-title")) {
                return el.text();
            }
        }
        return null;
    }

    private String getAuthorElement(Element article) {
        for(Element el: article.children()) {
            //System.out.println("[MA CRAWLER] Found AUTHOR selector: " + el.cssSelector());
            if(el.hasClass("author")) {
                //System.out.println("[MA CRAWLER] Found AUTOR text: " + el.text());
                return el.text();
            }
        }
        return "Maka Angola";
    }
}
