/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import db.news.NewsArticle;

/**
 *
 * @author zua
 */
public class UpdateBodyMessage {

    private final FlexBody body;
    private final Iterable<NewsArticle> nodes;

    public UpdateBodyMessage(FlexBody body, Iterable<NewsArticle> nodes) {
        this.body = body;
        this.nodes = nodes;
    }

    public FlexBody getBody() {
        return body;
    }

    public Iterable<NewsArticle> getNodes() {
        return nodes;
    }
    
    
    
}
