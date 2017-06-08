/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utils;

import flex.backend.news.services.FlexUserService;
import flex.backend.news.services.NewsApiService;
import flex.backend.news.services.NewsArticleService;
import flex.backend.news.services.NewsAuthorService;
import flex.backend.news.services.NewsSourceService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zua
 */
public class ServiceLocator {
    private static final String PREFIX = "java:global/flex-app/";
    public static final String NEWS_API_SERVICE     = PREFIX + NewsApiService.class.getSimpleName();
    public static final String NEWS_ARTICLE_SERVICE = PREFIX + NewsArticleService.class.getSimpleName();
    public static final String NEWS_SOURCE_SERVICE  = PREFIX + NewsSourceService.class.getSimpleName();
    public static final String NEWS_AUTHOR_SERVICE  = PREFIX + NewsAuthorService.class.getSimpleName();
    public static final String FLEX_USER_SERVICE    = PREFIX + FlexUserService.class.getSimpleName();
    
    private static InitialContext context;

    private static ServiceLocator instance;
    
    private ServiceLocator() {}
    
    public static ServiceLocator getInstance() {
        if(instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }
    
    private Object findService(String name) {
        try {
            if(context == null) {
                context = new InitialContext();
            }
            return context.lookup(name);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
  
    public NewsArticleService findArticlesService() {
        return (NewsArticleService) findService(NEWS_ARTICLE_SERVICE);
    }

    public NewsSourceService findSourcesService() {
        return (NewsSourceService) findService(NEWS_SOURCE_SERVICE);
    }

    public NewsAuthorService findAuthorsService() {
        return (NewsAuthorService) findService(NEWS_AUTHOR_SERVICE);
    }
    
    public NewsApiService findNewsApiService() {
        return (NewsApiService) findService(NEWS_API_SERVICE);
    }
    
    public FlexUserService findUserService() {
        return (FlexUserService) findService(FLEX_USER_SERVICE);
    }

    public void setInitialContext(InitialContext aContext) {
        context = aContext;
    }


}
