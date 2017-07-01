/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.histories;

import db.news.GraphEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author zua
 */
public class FlexEvent extends GraphEntity {
    private String what;
    private Locale where;
    private Date when; 
    private Set<FlexReference> references;

    public FlexEvent() {
        references = new HashSet();
    }
    
    

    @Override
    public String getPropertyName() {
        return "what";
    }

    @Override
    public String getPropertyValue() {
        return what; 
    }

    @Override
    public boolean hasUrl() {
        return false;
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
