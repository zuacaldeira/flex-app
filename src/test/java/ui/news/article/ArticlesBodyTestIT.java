/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news.article;

import db.news.NewsArticle;
import db.news.NewsAuthor;
import ui.news.UITest;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
        List<NewsArticle> articles = new LinkedList<>();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        Set<NewsAuthor> authors = new HashSet<>();
        authors.add(new NewsAuthor("name"));
        article.setAuthors(authors);
        articles.add(article);
    }
    
}
