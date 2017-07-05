/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import db.news.GraphEntity;

/**
 *
 * @author zua
 */
public class UpdateBodyMessage<T extends GraphEntity> {

    private final FlexBody body;
    private final Iterable<T> nodes;

    public UpdateBodyMessage(FlexBody body, Iterable<T> nodes) {
        this.body = body;
        this.nodes = nodes;
    }

    public FlexBody getBody() {
        return body;
    }

    public Iterable<T> getNodes() {
        return nodes;
    }
    
    
    
}
