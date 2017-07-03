/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.news;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author zua
 */
@NodeEntity
public class FlexUser extends GraphEntity {

    @Index(unique = true)
    private String username;
    private String password;
    private Set<GraphEntity> read;
    private Set<GraphEntity> favorite;
    private Set<GraphEntity> fake;

    public FlexUser() {
        read = new HashSet<>();
        favorite = new HashSet<>();
        fake = new HashSet<>();
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

    public void read(GraphEntity article) {
        read.add(article);
    }

    public boolean hasRead(GraphEntity article) {
        return read.contains(article);
    }

    public void favorite(GraphEntity article) {
        favorite.add(article);
    }

    public boolean hasFavorite(GraphEntity article) {
        return favorite.contains(article);
    }

    public void fake(GraphEntity article) {
        fake.add(article);
    }

    public boolean hasFake(GraphEntity article) {
        return fake.contains(article);
    }

    @Override
    public boolean hasUrl() {
        return false;
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}
