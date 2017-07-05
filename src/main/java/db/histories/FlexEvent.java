/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.histories;

import com.vaadin.tapio.googlemaps.client.LatLon;
import db.news.FlexUser;
import db.news.GraphEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author zua
 */
public class FlexEvent extends GraphEntity {
    
    private String what;
    private Date when; 
    private String where;
    private LatLon latLon;
    private String details;
    private Set<FlexReference> references;    
    private FlexUser owner;

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
        return null;
    }

    public void setOwner(FlexUser owner) {
        this.owner = owner;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Set<FlexReference> getReferences() {
        return references;
    }

    public void setReferences(Set<FlexReference> references) {
        this.references = references;
    }

    public void setTitle(String value) {
        this.what = value;
    }

    public String getTitle() {
        return what;
    }

    public FlexUser getOwner() {
        return owner;
    }

    public LatLon getLatLon() {
        return latLon;
    }

    public void setLatLon(LatLon latLon) {
        this.latLon = latLon;
    }
    
    
}

