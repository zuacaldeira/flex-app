/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import services.histories.FlexVideoService;
import services.news.FlexUserService;
import services.news.NewsApiService;
import services.news.NewsArticleService;
import services.news.NewsAuthorService;
import services.news.NewsSourceService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import services.histories.FlexEventService;
import services.histories.FlexNoteService;

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
    public static final String FLEX_VIDEO_SERVICE   = PREFIX + FlexVideoService.class.getSimpleName();
    public static final String FLEX_NOTE_SERVICE    = PREFIX + FlexNoteService.class.getSimpleName();
    public static final String FLEX_EVENT_SERVICE    = PREFIX + FlexEventService.class.getSimpleName();
    
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

    public FlexVideoService findVideoService() {
        return (FlexVideoService) findService(FLEX_VIDEO_SERVICE);
    }

    public FlexNoteService findNotesService() {
        return (FlexNoteService) findService(FLEX_NOTE_SERVICE);
    }

    public FlexEventService findEventService() {
        return (FlexEventService) findService(FLEX_EVENT_SERVICE);
    }

    public void setInitialContext(InitialContext aContext) {
        context = aContext;
    }



}