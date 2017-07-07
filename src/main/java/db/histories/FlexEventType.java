/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.histories;

import db.news.GraphEntity;
import java.util.Objects;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class FlexEventType extends GraphEntity {
    
    private String name;
    
    public FlexEventType() {
    }

    public FlexEventType(String name) {
        this.name = name;
    }
    
    @Override
    public String getPropertyName() {
        return "name";
    }

    @Override
    public String getPropertyValue() {
        return name; 
    }

    @Override
    public boolean hasUrl() {
        return false;
    }

    @Override
    public String getUrl() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlexEventType other = (FlexEventType) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}

