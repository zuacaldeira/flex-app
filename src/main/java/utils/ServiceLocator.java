/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import backend.services.auth.FlexUserService;
import backend.services.books.FlexAmazonService;
import backend.services.books.FlexBooksService;
import backend.services.news.NewsArticleService;
import backend.services.news.NewsAuthorService;
import backend.services.news.NewsSourceService;
import backend.services.news.NewsTagService;

/**
 *
 * @author zua
 */
public class ServiceLocator {

    public static final String NEWS_API_SERVICE             = "java:global/flex-app/NewsApiService";
    public static final String NEWS_ARTICLE_SERVICE         = "java:global/flex-app/NewsArticleService";
    public static final String NEWS_SOURCE_SERVICE          = "java:global/flex-app/NewsSourceService";
    public static final String NEWS_AUTHOR_SERVICE          = "java:global/flex-app/NewsAuthorService";
    public static final String NEWS_TAG_SERVICE             = "java:global/flex-app/NewsTagService";
    public static final String FLEX_USER_SERVICE            = "java:global/flex-app/FlexUserService";
    public static final String FLEX_ADVERTISEMENT_SERVICE   = "java:global/flex-app/FlexAdvertisementService";
    public static final String FLEX_AMAZON_SERVICE          = "java:global/flex-app/FlexAmazonService";
    public static final String FLEX_PEOPLE_SERVICE          = "java:global/flex-app/FlexPeopleService";
    public static final String FLEX_BOOK_SERVICE            = "java:global/flex-app/FlexBooksService";

    private static ServiceLocator INSTANCE;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceLocator();
        }
        return INSTANCE;
    }

    private Object findService(String name) throws NamingException {
        InitialContext context = new InitialContext();
        Object o = context.lookup(name);
        return o;
    }

    public NewsArticleService findArticlesService() {
        try {
            return (NewsArticleService) findService(NEWS_ARTICLE_SERVICE);
        } catch (NamingException nx) {
            return new NewsArticleService();
        }
    }

    public NewsSourceService findSourcesService() {
        try {
            return (NewsSourceService) findService(NEWS_SOURCE_SERVICE);
        } catch (NamingException nx) {
            return new NewsSourceService();
        }
    }

    public NewsAuthorService findAuthorsService() {
        try {
            return (NewsAuthorService) findService(NEWS_AUTHOR_SERVICE);
        } catch (NamingException nx) {
            return new NewsAuthorService();
        }
    }

    public FlexUserService findUserService() {
        try {
            return (FlexUserService) findService(FLEX_USER_SERVICE);
        } catch (NamingException nx) {
            return new FlexUserService();
        }
    }

    public NewsTagService findTagService() {
        try {
            return (NewsTagService) findService(NEWS_TAG_SERVICE);
        } catch (NamingException nx) {
            return new NewsTagService();
        }
    }

    public FlexAmazonService findAmazonService() {
        try {
            return (FlexAmazonService) findService(FLEX_AMAZON_SERVICE);
        } catch (NamingException ex) {
            return new FlexAmazonService();
        }
    }

    public FlexBooksService findBooksService() {
        try {
            return (FlexBooksService) findService(FLEX_BOOK_SERVICE);
        } catch (NamingException ex) {
            return new FlexBooksService();
        }
    }

}
