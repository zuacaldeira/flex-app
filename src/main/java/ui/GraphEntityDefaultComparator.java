/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import db.news.NewsArticle;
import java.util.Comparator;

/**
 *
 * @author zua
 * @param <T>
 */
public class GraphEntityDefaultComparator<T extends NewsArticle> implements Comparator<T> {

    public GraphEntityDefaultComparator() {
    }

    @Override
    public int compare(T o1, T o2) {
        return - o1.getPublishedAt().compareTo(o2.getPublishedAt());
    }
    
}
