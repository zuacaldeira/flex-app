/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsAuthor;
import flex.frontend.ui.news.UITest;
import java.util.LinkedList;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class ArticlesBodyTest extends UITest {
    
    public ArticlesBodyTest() {
    }

    /**
     * Test of addArticles method, of class ArticlesBody.
     */
    @Test
    @Ignore
    public void testAddArticles()  {
        System.out.println("addArticles");
        ArticlesBody body = new ArticlesBody();
        
        List<NewsArticle> articles = new LinkedList<>();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", "publishedAt");
        article.setAuthor(new NewsAuthor("name"));
        articles.add(article);
        
    }
    
}
