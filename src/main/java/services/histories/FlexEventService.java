/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.histories;

import db.histories.FlexEvent;
import services.news.AbstractDBService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 *
 * @author zua
 */
@Stateless
@LocalBean
public class FlexEventService extends AbstractDBService<FlexEvent> {

    @Override
    public Class<FlexEvent> getClassType() {
        return FlexEvent.class;
    }
    
    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "when");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "when");
    }
    

    @Override
    public FlexEvent update(FlexEvent dbEntity, FlexEvent newEntity) {
        if(newEntity.getTitle() != null) {
            dbEntity.setTitle(newEntity.getTitle());
        }
        if(newEntity.getDetails() != null) {
            dbEntity.setDetails(newEntity.getDetails());
        }
        if(newEntity.getOwner() != null) {
            dbEntity.setOwner(newEntity.getOwner());
        }
        if(newEntity.getReferences() != null) {
            dbEntity.getReferences().addAll(newEntity.getReferences());
        }
        if(newEntity.getWhat()!= null) {
            dbEntity.setWhat(newEntity.getWhat());
        }
        if(newEntity.getWhen()!= null) {
            dbEntity.setWhen(newEntity.getWhen());
        }
        if(newEntity.getWhere()!= null) {
            dbEntity.setWhere(newEntity.getWhere());
        }
        return dbEntity;
    }

}
