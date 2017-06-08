/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import flex.frontend.ui.news.NewsBody;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsAuthor;
import flex.frontend.ui.news.UITest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class ArticlesBodyTestIT extends UITest {
    
    public ArticlesBodyTestIT() {
    }

    /**
     * Test of addArticles method, of class NewsBody.
     */
    @Test
    public void testAddArticles()  {
        System.out.println("addArticles");
        NewsBody body = new NewsBody();
        List<NewsArticle> articles = new LinkedList<>();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        article.setAuthor(new NewsAuthor("name"));
        articles.add(article);
    }
    
}
