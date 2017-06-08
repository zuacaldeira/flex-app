/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.FlexUser;
import flex.backend.news.db.NewsArticle;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 *
 * @author zua
 */
@Stateless
public class FlexUserService extends AbstractDBService<FlexUser> {

    @Override
    public FlexUser update(FlexUser dbEntity, FlexUser newEntity) {
        if(newEntity.getUsername().equals(dbEntity.getUsername())) {
            if(newEntity.getPassword() != null) {
                dbEntity.setPassword(newEntity.getPassword());
            }
        }
        
        return dbEntity;
    }

    @Override
    public Class<FlexUser> getClassType() {
        return FlexUser.class;
    }

    public FlexUser login(FlexUser user) {
        FlexUser u = find(user);
        if(u != null && u.getPassword().equals(user.getPassword())) {
            return u;
        }
        
        return null;
    }

    @Override
    public SortOrder getSortOrder() {
        return new SortOrder().add("username");
    }
    
    public void markAsRead(String username, NewsArticle article) {
        FlexUser user = find(new FlexUser(username, null));
        user.read(article);
        save(user);
    }
    
}
