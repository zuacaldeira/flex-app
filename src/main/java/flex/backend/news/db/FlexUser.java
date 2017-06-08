/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class FlexUser extends GraphEntity{

    private String username;
    private String password;
    private Set<NewsArticle> read;

    public FlexUser() {
        read = new HashSet<>();
    }

    public FlexUser(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }
    
    @Override
    public String getPropertyName() {
        return "username";
    }

    @Override
    public String getPropertyValue() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void read(NewsArticle article) {
        read.add(article);
    }

    public boolean hasRead(NewsArticle article) {
        return read.contains(article);
    }
    
    
    
    
}
