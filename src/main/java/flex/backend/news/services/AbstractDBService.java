/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.GraphEntity;
import java.util.Collection;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class AbstractDBService<T extends GraphEntity> implements DBService<T> {

    
    
    @Override
    public final Iterable<T> findAll() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.loadAll(getClassType(), getSortOrder(), new Pagination(0, 100), 2);
    }

    @Override
    public final T find(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Graph entity ID cannot be null");
        }
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.load(getClassType(), id, 2);
    }
    
    @Override
    public final T find(T object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Filter filter = new Filter(object.getPropertyName(), ComparisonOperator.EQUALS, object.getPropertyValue());
        Collection<T> collection = session.loadAll(
                                    getClassType(),
                                    new Filters().add(filter),
                                    2);
        if(collection != null && !collection.isEmpty() ) {
            return collection.iterator().next();
        }
        else {
            return null;
        }
    }
    
    @Override
    public final T save(T object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.save(object);
        return find(object);
    }
    
    @Override
    public final void delete(Long id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.delete(find(id));
    }
    
    @Override
    public final long count() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.countEntitiesOfType(getClassType());
    }

    @Override
    public boolean contains(T object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Filter filter = new Filter(object.getPropertyName(), ComparisonOperator.EQUALS, object.getPropertyValue());
        return !session.loadAll(getClassType(),
                               new Filters().add(filter), 
                               2).isEmpty();
    }

    @Override
    public T update(T object) {
        if(object.getId() == null) {
            throw new IllegalArgumentException("Cannot update object not in db. Use save() to store the object in the database");
        }
        return save(update(find(object), object));
    }
    
    
    
    public abstract T update(T dbEntity, T newEntity);
    public abstract SortOrder getSortOrder();

}
