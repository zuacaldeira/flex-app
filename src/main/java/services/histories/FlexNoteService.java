/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.histories;

import db.histories.FlexNote;
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
public class FlexNoteService extends AbstractDBService<FlexNote> {

    @Override
    public Class<FlexNote> getClassType() {
        return FlexNote.class;
    }
    
    @Override
    public SortOrder getSortOrder() {
        return new SortOrder().add("title");
    }

    @Override
    public FlexNote update(FlexNote dbEntity, FlexNote newEntity) {
        if(newEntity.getTitle() != null) {
            dbEntity.setTitle(newEntity.getTitle());
        }
        if(newEntity.getContent() != null) {
            dbEntity.setContent(newEntity.getContent());
        }
        return dbEntity;
    }

}
