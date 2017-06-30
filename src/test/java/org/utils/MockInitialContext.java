/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utils;

import utils.ServiceLocator;
import services.news.NewsApiService;
import services.news.NewsArticleService;
import services.news.NewsAuthorService;
import services.news.NewsSourceService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zua
 */
public class MockInitialContext extends InitialContext {

    public MockInitialContext() throws NamingException {
    }
    
    

    @Override
    public Object lookup(String name) throws NamingException {
        if(name.equals(ServiceLocator.NEWS_API_SERVICE)) {
            NewsApiService apiService = new NewsApiService();
            apiService.setArticlesService(new NewsArticleService());
            apiService.setAuthorsService(new NewsAuthorService());
            apiService.setSourcesService(new NewsSourceService());
            return apiService;
        }
        else if(name.equals(ServiceLocator.NEWS_ARTICLE_SERVICE)) {
            return new NewsArticleService();
        }
        else if(name.equals(ServiceLocator.NEWS_SOURCE_SERVICE)) {
            return new NewsSourceService();
        }
        else if(name.equals(ServiceLocator.NEWS_AUTHOR_SERVICE)) {
            return new NewsAuthorService();
        }
        else{
            throw new NamingException(name);
        }
    }
    
}
