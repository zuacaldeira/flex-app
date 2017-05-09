/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utils;

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
    
    private static InitialContext context;

    private ServiceLocator() {}
    
    private static Object findService(String name) {
        try {
            if(context == null) {
                context = new InitialContext();
            }
            return context.lookup(name);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
  
    public static NewsArticleService findArticlesService() {
        return (NewsArticleService) findService(NEWS_ARTICLE_SERVICE);
    }

    public static NewsSourceService findSourcesService() {
        return (NewsSourceService) findService(NEWS_SOURCE_SERVICE);
    }

    public static NewsAuthorService findAuthorsService() {
        return (NewsAuthorService) findService(NEWS_AUTHOR_SERVICE);
    }
    
    public static NewsApiService findNewsApiService() {
        return (NewsApiService) findService(NEWS_API_SERVICE);
    }
    
    public static void setInitialContext(InitialContext aContext) {
        context = aContext;
    }

}
