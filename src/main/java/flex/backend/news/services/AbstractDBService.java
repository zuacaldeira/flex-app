/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.GraphEntity;
import java.util.HashMap;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.session.Session;
import org.utils.FlexUtils;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class AbstractDBService<T extends GraphEntity> implements DBService<T> {

    public final int MAX_SIZE = 25;

    
    
    @Override
    public final Iterable<T> findAll() {
        return findAll(MAX_SIZE);
    }

    @Override
    public final Iterable<T> findAll(int limit) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.loadAll(getClassType(), getSortOrder(), new Pagination(0, limit), 2);
    }

    @Override
    public final Iterable<T> findAll(String property, Object value) {
        return findAll(property, value, MAX_SIZE);
    }

    @Override
    public final Iterable<T> findAll(String property, Object value, int limit) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Filter filter = new Filter(property, ComparisonOperator.EQUALS, value);
        return session.loadAll(getClassType(), filter, getSortOrder(), new Pagination(0, limit), 2);
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
        String classname = getClassType().getSimpleName();
        String query = "MATCH (n:" + classname + ")";
        query += " WHERE n." + object.getPropertyName() + "=" + FlexUtils.getInstance().wrapUp(object.getPropertyValue());
        query += " RETURN n";
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return (T) session.queryForObject(getClassType(), query, new HashMap());
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

    protected Session getSession() {
        return Neo4jSessionFactory.getInstance().getNeo4jSession();
    }
}
