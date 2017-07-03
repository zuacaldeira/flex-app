/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import utils.Neo4jSessionFactory;
import db.news.FlexUser;
import db.news.GraphEntity;
import java.util.HashMap;
import javax.ejb.EJB;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import utils.FlexUtils;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class AbstractDBService<T extends GraphEntity> implements DBService<T> {

    public final int MAX_SIZE = 25;

    @EJB
    private FlexUserService userService;
    
    
    @Override
    public final Iterable<T> findAll() {
        return findAll(MAX_SIZE);
    }

    @Override
    public final Iterable<T> findAll(int limit) {
        return findAll(limit, getSortOrder());
    }

    public final Iterable<T> findAll(int limit, SortOrder sortOrder) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.loadAll(getClassType(), sortOrder, new Pagination(0, limit), 2);
    }

    public final Iterable<T> findAll(int limit, Filter filter, SortOrder sortOrder) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.loadAll(getClassType(), filter, sortOrder, new Pagination(0, limit), 2);
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
        Iterable<T> dbObjects = session.query(getClassType(), query, new HashMap());
        if(dbObjects.iterator().hasNext()) {
            return find(dbObjects.iterator().next().getId());
        }
        return null;
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
    
    public void markAsRead(String username, T entity) {
        FlexUser user = userService.find(new FlexUser(username, null));
        user.read(entity);
        userService.save(user);
    }

    public void markAsFavorite(String username, T entity) {
        FlexUser user = userService.find(new FlexUser(username, null));
        user.favorite(entity);
        userService.save(user);
    }

    public void markAsFake(String username, T entity) {
        FlexUser user = userService.find(new FlexUser(username, null));
        user.fake(entity);
        userService.save(user);
    }


}
