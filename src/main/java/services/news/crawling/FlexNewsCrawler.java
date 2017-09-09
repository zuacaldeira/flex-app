/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news.crawling;

import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import services.news.NewsArticleService;
import services.news.NewsAuthorService;
import services.news.NewsSourceService;
import utils.FlexLogger;

/**
 *
 * @author zua
 */
public abstract class FlexNewsCrawler {

    @EJB
    private NewsArticleService articlesService;
    @EJB
    private NewsAuthorService authorsService;
    @EJB
    private NewsSourceService sourcesService;

    private FlexLogger logger;
    
    public FlexNewsCrawler() {
        logger = new FlexLogger(getClass());
    }
    
    public void crawlWebsite(String url, NewsSource source) {
        try {
            logger.info("Loading articles from: %s", url);
            Set<String> visited = new HashSet<>();
            visited.add(url);
            crawlUrl(openDocument(url), source, visited);
        } catch (Exception e) {
            // Do nothing
            //logger.info("[%20s] !!ERROR!! %s", e.getMessage());
        }
    }

    /**
     * Connects to the web address.
     *
     * @param url A web address url, starting with http(s).
     * @return The top document representing the content of web address.
     */
    protected Document openDocument(String url) {
        try {
            return Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (Exception e) {
            logger.log("\tERROR - Couldn't open document %s caused by: %s", url, e.getMessage());
            return null;
        }
    }

    private NewsSource getSource() {
        NewsSource source = getMySource();
        NewsSource dbSource = sourcesService.findSourceBySourceId(source.getSourceId());
        return (dbSource != null) ? dbSource : source;
    }

    private void crawlUrl(Document document, final NewsSource source, Set<String> visitedUrls) {
        if (document != null) {
            Elements articles = getArticles(document);
            for (Element article : articles) {
                //prettyPrint(article);
                try {
                    importArticle(article, source);
                } catch (Exception e) {
                    e.printStackTrace();
                    //
                }
            }
        }
    }

    private void importArticle(Element article, NewsSource source) {
        //prettyPrint(article);
        logger.log("Processing article: %s", article.text());

        String articleUrl = getUrl(article);
        if(articleUrl == null) {
            logger.info("\tMissing url: %s", article.text());
            return;
        }
        logger.log("URL: %s", articleUrl);

        
        Document document = openDocument(articleUrl);
        if(document == null) {
            logger.info("\tCould't open url: %s", articleUrl);
            return;
        }
        
        String title = getTitle(document);
        if(title == null) {
            logger.info("\tMissing title: %s", article.text());
            return;
        }
        logger.log("TITLE: %s", title);

        String imageUrl = getImageUrl(document);
        if(imageUrl == null) {
            logger.log("\tMissing image url: %s", article.text());
            //return;
        }
        logger.log("IMAGE: %s", imageUrl);

        String description = getContent(document);
        if(description == null) {
            logger.info("\tMissing description: %s", article.text());
            return;
        }
        logger.log("DESCRIPTION: %s", description);

        Date date = getPublishedAt(document);
        if(date == null) {
            logger.info("\tMissing published at: %s", article.text());
            return;
        }
        logger.log("DATE: %s", date);

        Set<NewsAuthor> authors = getNewsAuthors(getAuthors(document));
        if(authors == null) {
            logger.info("\tMissing authors: %s", article.text());
            return;
        }
        logger.log("AUTHORS: %s", authors);
        
        boolean inDb = articlesService.findArticleByTitle(title) != null;
        if(inDb) {
            logger.log("\tIgnored old article: %s", title);
            return;
        }
        
        saveArticle(articleUrl, title, imageUrl, description, date, authors, source);
    }
    
    private void saveArticle(String articleUrl, String title, String imageUrl, String description, Date date, Set<NewsAuthor> authors, NewsSource source) {
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setSourceId(source.getSourceId());

        newsArticle.setTitle(title);
        newsArticle.setUrl(articleUrl);

        newsArticle.setImageUrl(imageUrl);
        newsArticle.setPublishedAt(date);
        newsArticle.setDescription(description);

        newsArticle.setAuthors(authors);
        source.setCorrespondents(authors);
        try {
            articlesService.save(newsArticle);
            logger.info("\tStored new article: %s", newsArticle.getTitle());
        } catch (Exception e) {
            logger.log("\tERROR storing article: %s", e.getMessage());
        }
    }
    public void prettyPrint(Element article) {
        System.out.println("--> INSIDE prettyPrint()");
        article.getAllElements().forEach(el -> {
            // if(el.hasClass("post-title")) {
            System.out.println("CSS selector: " + el.cssSelector());
            System.out.println("  Attributes: " + el.attributes());
            //}
        });
        System.out.println();
    }

    public abstract NewsSource getMySource();

    protected abstract Elements getArticles(Document document);

    public String getUrl(Element article) {
        UrlElement urlElement = getUrlElement(article);
        if (urlElement != null) {
            return urlElement.getValue();
        }
        return null;
    }

    public UrlElement getUrlElement(Element article) {
        return new UrlElement(getUrlValue(article));
    }

    protected abstract String getUrlValue(Element article);

    public String getTitle(Document document) {
        TitleElement titleElement = getTitleElement(document);
        if (titleElement != null) {
            return titleElement.getValue();
        }
        return null;
    }

    public TitleElement getTitleElement(Document document) {
        return new TitleElement(getTitleValue(document));
    }

    protected abstract String getTitleValue(Document document);

    public String getImageUrl(Document document) {
        ImageUrlElement imageUrlElement = getImageUrlElement(document);
        if (imageUrlElement != null) {
            return imageUrlElement.getValue();
        }
        return null;
    }

    public ImageUrlElement getImageUrlElement(Document document) {
        return new ImageUrlElement(getImageUrlValue(document));
    }

    protected abstract String getImageUrlValue(Document document);

    public String getContent(Document document) {
        ContentElement contentElement = getContentElement(document);
        if (contentElement != null) {
            return contentElement.getValue();
        }
        return null;
    }

    public ContentElement getContentElement(Document document) {
        return new ContentElement(getContentValue(document));
    }

    protected abstract String getContentValue(Document document);

    public Set<String> getAuthors(Document document) {
        AuthorsElement authorsElement = getAuthorsElement(document);
        if (authorsElement != null && !authorsElement.getAuthors().isEmpty()) {
            return authorsElement.getAuthors();
        }
        Set<String> result = new HashSet<>();
        result.add(getSource().getName());
        return result;
    }

    public AuthorsElement getAuthorsElement(Document document) {
        return new AuthorsElement(getAuthorsValue(document));
    }

    protected abstract String getAuthorsValue(Document document);

    protected String getFullImageUrl(String src) {
        if(!src.startsWith(getSource().getUrl())) {
            return getSource().getUrl() + src;
        }
        return src;
    }

    private Set<NewsAuthor> getNewsAuthors(Set<String> names) {
        Set<NewsAuthor> result = new HashSet<>();
        if(names.isEmpty()) {
            result.add(findAuthor(getMySource().getName()));
        }
        else {
            names.forEach(name -> {
                if(name != null && !name.isEmpty()) {
                    result.add(findAuthor(name));
                }
            });
        }
        return result;
    }

    private NewsAuthor findAuthor(String name) {
        NewsAuthor author = authorsService.findAuthorByName(name);
        if (author != null) {
            return author;
        } else {
            return new NewsAuthor(name);
        }
    }
    
    public Date getPublishedAt(Document document) {
        TimeElement timeElement = getTimeElement(document);
        if (timeElement != null) {
            return timeElement.getDate();
        }
        return new Date();
    }

    public TimeElement getTimeElement(Document document) {
        return new TimeElement(getTimeValue(document), getSource().getLanguage());
    }

    protected abstract String getTimeValue(Document document);

    protected String normalizeTime(String inputPattern, String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(inputPattern, new Locale(getMySource().getLanguage()));
            Date date = format.parse(dateString);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", new Locale(getMySource().getLanguage()));
            return format2.format(date);
        } catch (ParseException ex) {
            logger.error("Parsing error during time normalization: ", ex.getMessage());
        }
        return null;
    }

    public FlexLogger getLogger() {
        return logger;
    }


}
