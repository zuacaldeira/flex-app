/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import services.FlexUserServiceInterface;
import services.NewsArticleServiceInterface;
import services.NewsAuthorServiceInterface;
import services.NewsSourceServiceInterface;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

    private static ServiceLocator INSTANCE;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new ServiceLocator();
        }
        return INSTANCE;
    }
    private Object findService(String name) {
        try {
            InitialContext context = new InitialContext();
            Object o = context.lookup(name);
            return o;
        } catch (NamingException ex) {
            throw new ServiceNotFoundException();
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

}
