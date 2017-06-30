/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.NewsArticle;
import java.util.Comparator;

/**
 *
 * @author zua
 */
public class NewsArticleComparator implements Comparator<NewsArticle> {

    @Override
    public int compare(NewsArticle o1, NewsArticle o2) {
        return -o1.getPublishedAt().compareTo(o2.getPublishedAt());
    }

    
}
