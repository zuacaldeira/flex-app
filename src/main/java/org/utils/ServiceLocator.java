/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utils;

import flex.backend.news.services.NewsArticleService;
import flex.backend.news.services.NewsAuthorsService;
import flex.backend.news.services.NewsSourceService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zua
 */
public class ServiceLocator {
    private static final String NEWS_DATABASE_UPDATER = "java:global/flex-app/NewsDatabaseUpdater";
    private static final String NEWS_ARTICLE_SERVICE = "java:global/flex-app/NewsArticleService";
    private static final String NEWS_SOURCE_SERVICE = "java:global/flex-app/NewsSourceService";
    private static final String NEWS_AUTHOR_SERVICE = "java:global/flex-app/NewsAuthorsService";
    
    private static Object findService(String name) {
        try {
            InitialContext ctx = new InitialContext();
            return ctx.lookup(name);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
  
    public static NewsArticleService findNewsArticleService() {
        return (NewsArticleService) findService(NEWS_ARTICLE_SERVICE);
    }

    public static NewsSourceService findNewsSourceService() {
        return (NewsSourceService) findService(NEWS_SOURCE_SERVICE);
    }

    public static NewsAuthorsService findAuthorsService() {
        return (NewsAuthorsService) findService(NEWS_AUTHOR_SERVICE);
    }

}
