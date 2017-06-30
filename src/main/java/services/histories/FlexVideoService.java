/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.histories;

import services.news.AbstractDBService;
import db.histories.FlexVideo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 *
 * @author zua
 */
@Stateless
@LocalBean
public class FlexVideoService extends AbstractDBService<FlexVideo> {

    @Override
    public Class<FlexVideo> getClassType() {
        return FlexVideo.class;
    }
    
    @Override
    public SortOrder getSortOrder() {
        return new SortOrder().add("title");
    }

    @Override
    public FlexVideo update(FlexVideo dbEntity, FlexVideo newEntity) {
        if(newEntity.getUrl() != null) {
            dbEntity.setUrl(newEntity.getUrl());
        }
        
        return dbEntity;
    }

}
