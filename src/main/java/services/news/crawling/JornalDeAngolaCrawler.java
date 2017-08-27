/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news.crawling;

import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import services.news.NewsArticleService;
import services.news.NewsAuthorService;
import services.news.NewsSourceService;
import utils.FlexUtils;

/**
 *
 * @author zua
 */
@Singleton
public class JornalDeAngolaCrawler {

    @EJB
    private NewsArticleService articlesService;
    @EJB
    private NewsAuthorService authorsService;
    @EJB
    private NewsSourceService sourcesService;

    private final String JORNAL_DE_ANGOLA_HOME = "http://jornaldeangola.sapo.ao";

    @Schedule(hour = "*", minute = "0, 15, 30, 45")
    public void crawlSet1() {
        crawlWebsite(JORNAL_DE_ANGOLA_HOME);
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/politica");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/economia");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/cultura");
    }
    
    @Schedule(hour = "*", minute = "5, 20, 35, 50")
    public void crawlSet2() {
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/reportagem");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/opiniao");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/sociedade");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/gente");
    }
        
    @Schedule(hour = "*", minute = "10, 25, 40, 55")
    public void crawlSet3() {
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/desporto");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/provincias");
        crawlWebsite(JORNAL_DE_ANGOLA_HOME + "/mundo");
    }

    private void crawlWebsite(String url) {
        try {
            System.out.println("------------------------------------------------");
            System.out.println("[JA CRAWLER] START CRAWLING: " + url);
            System.out.println("------------------------------------------------");
            List<String> visited = new LinkedList<>();
            visited.add(url);

            NewsSource source = getSource();
            crawlUrlJornalDeAngola(url, source);
        } catch (Exception e) {
            // Do nothing
        }
        System.out.println("[JA CRAWLER] END CRAWLING: " + url);
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
            return null;
        }
    }

    private void crawlUrlJornalDeAngola(String url, final NewsSource source) {
        Document document = openDocument(url);
        if (document != null) {
            Elements articles = document.select("article");
            for (Element article : articles) {
                //prettyPrint(article);
                System.out.println("[JA CRAWLER] Processing article " + article.text());
                try {
                    importArticle(article, source);
                    System.out.println("\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    //
                }
            }
        }
    }

    private NewsSource getSource() {
        String sourceId = "jornaldeangola";
        String name = "Jornal de Angola";
        String description = "";
        String url = "http://jornaldeangola.sapo.ao";
        String category = "geral";
        String language = "pt";
        String country = "ao";
        String logoUrl = "http://imgs.sapo.pt/jornaldeangola/2016/img/jornal-de-angola-2017.png";

        NewsSource source = new NewsSource(sourceId, name, description, url, category, language, country);
        source.setLogoUrl(logoUrl);

        NewsSource dbSource = sourcesService.findSourceBySourceId(sourceId);
        return (dbSource != null) ? dbSource : source;
    }

    private void importArticle(Element article, NewsSource source) {
        //prettyPrint(article);
        String url = getArticleUrl(article);
        String title = getArticleTitle(article, url);

        if (title != null && articlesService.findArticleByTitle(title) == null) {

            NewsArticle newsArticle = new NewsArticle();
            newsArticle.setSourceId(source.getSourceId());

            newsArticle.setTitle(title);
            newsArticle.setUrl(url);
            newsArticle.setImageUrl(getArticleImageUrl(article, url));
            newsArticle.setPublishedAt(getPublishedAt(article, url));
            newsArticle.setDescription(getArticleDescription(article, url));

            Set<NewsAuthor> authors = updateAuthors(article, url);
            newsArticle.setAuthors(authors);
            source.setCorrespondents(authors);

            if (newsArticle.getTitle() != null && newsArticle.getUrl() != null/* && newsArticle.getImageUrl() != null*/) {
                try {
                    articlesService.save(newsArticle);
                    System.out.println("[JA CRAWLER] \tArticle SAVED");
                } catch (Exception e) {
                    System.err.println("[JA CRAWLER] \tError while storing article: " + e.getMessage());
                }
            } else {
                System.out.println("[JA CRAWLER] \tIgnoring incomplete article: " + title);
            }
        }
    }

    private void prettyPrint(Element article) {
        System.out.println("--> INSIDE prettyPrint()");
        article.getAllElements().forEach(el -> {
            // if(el.hasClass("post-title")) {
            System.out.println("CSS selector: " + el.cssSelector());
            System.out.println("  Attributes: " + el.attributes());
            //}
        });
        System.out.println();
    }

    private String getArticleTitle(Element article, String articleUrl) {
        Element title = article.select("h3 > a").first();
        if (title != null) {
            System.out.println("[JA CRAWLER] Found title: " + title.text());
            return title.text();
        }
        return getArticleTitleInnerly(articleUrl);
    }

    private String getArticleTitleInnerly(String articleUrl) {
        Document document = openDocument(articleUrl);
        if (document != null) {
            Element title = document.select("main > article > h1").first();
            if (title != null) {
                System.out.println("[JA CRAWLER] Found title: " + title.text());
                return title.text();
            }
        }
        return null;
    }

    private Set<String> getArticleAuthors(Element article, String articleUrl) {
        Element author = article.select("p.info-autor").first();
        if (author != null && author.text() != null && !author.text().isEmpty()) {
            Set<String> names = extractAuthorName(author.text());
            System.out.println("[JA CRAWLER] Found authors: " + names);
            return names;
        }
        return getArticleAuthorsInnerly(articleUrl);
    }

    private Set<String> getArticleAuthorsInnerly(String articleUrl) {
        Document document = openDocument(articleUrl);
        if (document != null) {
            Elements ps = document.select("main > article > p.info-autor");
            for (Element p : ps) {
                if (p.classNames().contains("info-autor") && p.text() != null && !p.text().isEmpty()) {
                    Set<String> names = extractAuthorName(p.text());
                    System.out.println("[JA CRAWLER] Found authors: " + names);
                    return names;
                }
            }
        }

        Set<String> result = new HashSet<>();
        result.add("Jornal de Angola");
        return result;
    }

    private String getArticleImageUrl(Element article, String articleUrl) {
        Element image = article.select("div > img").first();
        if (image != null) {
            System.out.println("[JA CRAWLER] Found image: " + image.attr("src"));
            return image.attr("src");
        }
        return getArticleImageUrlInnerly(articleUrl);
    }

    private String getArticleImageUrlInnerly(String articleUrl) {
        Document document = openDocument(articleUrl);
        if (document != null) {
            Element image = document.select("main > article > figure > img").first();
            if (image != null) {
                System.out.println("[JA CRAWLER] Found image: " + image.attr("src"));
                return image.attr("src");
            }
        }
        return null;
    }

    private String getArticleUrl(Element article) {
        Element link = article.select("a").first();
        if (link != null) {
            System.out.println("[JA CRAWLER] Found url: " + link.absUrl("href"));
            return link.absUrl("href");
        }
        return null;
    }

    private Date getPublishedAt(Element article, String articleUrl) {
        Element time = article.select("date-time").first();
        if (time != null) {
            System.out.println("[JA CRAWLER] Found time: " + FlexUtils.getInstance().getDate(time.attr("datetime")));
            return FlexUtils.getInstance().getDate(time.attr("datetime"));
        }
        return getPublishedAtInnerly(articleUrl);
    }

    private Date getPublishedAtInnerly(String articleUrl) {
        Document document = openDocument(articleUrl);
        if (document != null) {
            Element time = document.select("main > article > p > time").first();
            if (time != null) {
                System.out.println("[JA CRAWLER] Found time: " + FlexUtils.getInstance().getDate(time.attr("datetime")));
                return FlexUtils.getInstance().getDate(time.attr("datetime"));
            }
        }
        return null;
    }

    private String getArticleDescription(Element article, String articleUrl) {
        Element content = article.select(".entry").first();
        if (content != null) {
            System.out.println("[JA CRAWLER] Found content: " + content.text());
            return content.text();
        }
        return getArticleDescriptionInnerly(articleUrl);
    }

    private String getArticleDescriptionInnerly(String articleUrl) {
        Document document = openDocument(articleUrl);
        if (document != null) {
            Elements ps = document.select("main > article > p");
            Element content = null;
            boolean next = false;
            for (Element p : ps) {
                if (p.hasClass("lead")) {
                    next = true;
                } else {
                    if (next) {
                        content = p;
                        break;
                    }
                }
            }
            if (content != null) {
                System.out.println("[JA CRAWLER] Found content: " + content.text());
                return content.text();
            }
        }
        return null;
    }

    private Set<NewsAuthor> updateAuthors(Element article, String articleUrl) {
        Set<NewsAuthor> result = new HashSet<>();
        Set<String> names = getArticleAuthors(article, articleUrl);
        for (String name : names) {
            NewsAuthor newsAuthor = new NewsAuthor(name);
            NewsAuthor dbAuthor = authorsService.find(newsAuthor);
            result.add((dbAuthor == null) ? newsAuthor : dbAuthor);
        }
        return result;
    }

    private Set<String> extractAuthorName(String text) {
        Set<String> result = new HashSet<>();

        if (text != null) {
            text = text.replace("|", ",").replace("*", "");
            String[] parts = text.split(",");
            for (String part : parts) {
                if (part.length() >= 4) {
                    result.add(part.trim());
                }
            }
        }

        return result;
    }
}
