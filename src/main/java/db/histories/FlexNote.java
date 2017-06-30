/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.histories;

import db.news.GraphEntity;

/**
 *
 * @author zua
 */
public class FlexNote extends GraphEntity {

    private String title;

    public FlexNote() {
    }

    @Override
    public String getPropertyName() {
        return "title";
    }

    @Override
    public String getPropertyValue() {
        return title;
    }

    @Override
    public boolean hasUrl() {
        return false;
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException();
    }
    
}
