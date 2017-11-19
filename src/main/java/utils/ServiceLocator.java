/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import services.FlexPeopleService;
import services.FlexUserServiceInterface;
import services.NewsArticleServiceInterface;
import services.NewsAuthorServiceInterface;
import services.NewsSourceServiceInterface;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import services.FlexAdvertisementService;
import services.FlexAdvertisementServiceInterface;
import services.FlexAmazonService;
import services.FlexAmazonServiceInterface;
import services.FlexBooksService;
import services.FlexBooksServiceInterface;
import services.FlexUserService;
import services.NewsArticleService;
import services.NewsAuthorService;
import services.NewsSourceService;
import services.FlexPeopleServiceInterface;

/**
 *
 * @author zua
 */
public class ServiceLocator {

    public static final String NEWS_API_SERVICE = "java:global/flex-app/NewsApiService!services.NewsApiServiceInterface";
    public static final String NEWS_ARTICLE_SERVICE = "java:global/flex-app/NewsArticleService!services.NewsArticleServiceInterface";
    public static final String NEWS_SOURCE_SERVICE = "java:global/flex-app/NewsSourceService!services.NewsSourceServiceInterface";
    public static final String NEWS_AUTHOR_SERVICE = "java:global/flex-app/NewsAuthorService!services.NewsAuthorServiceInterface";
    public static final String FLEX_USER_SERVICE = "java:global/flex-app/FlexUserService!services.FlexUserServiceInterface";
    public static final String FLEX_ADVERTISEMENT_SERVICE = "java:global/flex-app/FlexAdvertisementService!services.FlexAdvertisementServiceInterface";
    public static final String FLEX_AMAZON_SERVICE = "java:global/flex-app/FlexAmazonService!services.FlexAmazonServiceInterface";
    public static final String FLEX_PEOPLE_SERVICE = "java:global/flex-app/FlexPeopleService!services.FlexPeopleServiceInterface";
    public static final String FLEX_BOOK_SERVICE = "java:global/flex-app/FlexBooksService!services.FlexBooksServiceInterface";

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

    public NewsArticleServiceInterface findArticlesService() {
        try {
            return (NewsArticleServiceInterface) findService(NEWS_ARTICLE_SERVICE);
        } catch (NamingException nx) {
            return new NewsArticleService();
        }
    }

    public NewsSourceServiceInterface findSourcesService() {
        try {
            return (NewsSourceServiceInterface) findService(NEWS_SOURCE_SERVICE);
        } catch (NamingException nx) {
            return new NewsSourceService();
        }
    }

    public NewsAuthorServiceInterface findAuthorsService() {
        try {
            return (NewsAuthorServiceInterface) findService(NEWS_AUTHOR_SERVICE);
        } catch (NamingException nx) {
            return new NewsAuthorService();
        }
    }

    public FlexUserServiceInterface findUserService() {
        try {
            return (FlexUserServiceInterface) findService(FLEX_USER_SERVICE);
        } catch (NamingException nx) {
            return new FlexUserService();
        }
    }

    public FlexAdvertisementServiceInterface findAdvertisementService() {
        try {
            return (FlexAdvertisementServiceInterface) findService(FLEX_ADVERTISEMENT_SERVICE);
        } catch (NamingException ex) {
            return new FlexAdvertisementService();
        }
    }

    public FlexAmazonServiceInterface findAmazonService() {
        try {
            return (FlexAmazonServiceInterface) findService(FLEX_AMAZON_SERVICE);
        } catch (NamingException ex) {
            return new FlexAmazonService();
        }
    }

    public FlexPeopleServiceInterface findPersonService() {
        try {
            return (FlexPeopleServiceInterface) findService(FLEX_PEOPLE_SERVICE);
        } catch (NamingException ex) {
            return new FlexPeopleService();
        }
    }

    public FlexBooksServiceInterface findBooksService() {
        try {
            return (FlexBooksServiceInterface) findService(FLEX_PEOPLE_SERVICE);
        } catch (NamingException ex) {
            return new FlexBooksService();
        }
    }

}
