/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import backend.services.auth.FlexUserService;
import backend.services.news.NewsArticleService;
import backend.services.news.NewsAuthorService;
import backend.services.news.NewsSourceService;

/**
 *
 * @author zua
 */
public class MockInitialContext extends InitialContext {

    public MockInitialContext() throws NamingException {
    }
    
    

    @Override
    public Object lookup(String name) throws NamingException {
        if(name.equals(ServiceLocator.NEWS_ARTICLE_SERVICE)) {
            return new NewsArticleService(); 
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
        else if(name.equals(ServiceLocator.FLEX_USER_SERVICE)) {
            return new FlexUserService();
        }
        else{
            throw new NamingException(name);
        }
    }
    
}
