/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import services.news.FlexUserServiceInterface;
import services.news.NewsArticleServiceInterface;
import services.news.NewsAuthorServiceInterface;
import services.news.NewsSourceServiceInterface;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zua
 */
public class ServiceLocator {

    public static final String NEWS_API_SERVICE = "java:global/flex-app/NewsApiService!services.news.NewsApiServiceInterface";
    public static final String NEWS_ARTICLE_SERVICE = "java:global/flex-app/NewsArticleService!services.news.NewsArticleServiceInterface";
    public static final String NEWS_SOURCE_SERVICE = "java:global/flex-app/NewsSourceService!services.news.NewsSourceServiceInterface";
    public static final String NEWS_AUTHOR_SERVICE = "java:global/flex-app/NewsAuthorService!services.news.NewsAuthorServiceInterface";
    public static final String FLEX_USER_SERVICE = "java:global/flex-app/FlexUserService!services.news.FlexUserServiceInterface";

    private static InitialContext context;

    private static ServiceLocator instance;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    private Object findService(String name) {
        try {
            if (context == null) {
                context = new InitialContext();
            }
            Object o = context.lookup(name);
            return o;
        } catch (NamingException ex) {
            System.out.println("NOT FOUND " + name);
            return null;
        }
    }

    public NewsArticleServiceInterface findArticlesService() {
        return (NewsArticleServiceInterface) findService(NEWS_ARTICLE_SERVICE);
    }

    public NewsSourceServiceInterface findSourcesService() {
        return (NewsSourceServiceInterface) findService(NEWS_SOURCE_SERVICE);
    }

    public NewsAuthorServiceInterface findAuthorsService() {
        return (NewsAuthorServiceInterface) findService(NEWS_AUTHOR_SERVICE);
    }

    public FlexUserServiceInterface findUserService() {
        return (FlexUserServiceInterface) findService(FLEX_USER_SERVICE);
    }

    public void setInitialContext(InitialContext aContext) {
        context = aContext;
    }

}
