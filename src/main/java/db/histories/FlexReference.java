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
public class FlexReference extends GraphEntity {
    
    private String title;
    private String url;

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
        return url != null && url.length() > 0;
    }

    @Override
    public String getUrl() {
        return url;
    }
    
}
