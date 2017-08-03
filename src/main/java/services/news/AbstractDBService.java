/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import com.google.common.collect.Sets;
import utils.Neo4jSessionFactory;
import db.news.GraphEntity;
import java.util.HashMap;
import java.util.Set;
import javax.ejb.EJB;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import utils.FlexUtils;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class AbstractDBService<T extends GraphEntity> implements DBService<T> {

    public final int LIMIT = 100;

    @EJB
    private FlexUserService userService;
    
    
    @Override
    public final Set<T> findAll() {
        return findAllDesc();
    }
    
    @Override
    public final Set<T> findAllAsc() {
        String query = getFindAllQuery(null, null, null, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final Set<T> findAllDesc() {
        String query = getFindAllQuery(null, null, null, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final Set<T> findAll(int limit) {
        return findAllDesc(limit);
    }
    
    @Override
    public final Set<T> findAllAsc(int limit) {
        String query = getFindAllQuery(null, null, null, getSortOrderAsc(), limit);
        return executeQuery(query);
    }

    @Override
    public final Set<T> findAllDesc(int limit) {
        String query = getFindAllQuery(null, null, null, getSortOrderDesc(), limit);
        return executeQuery(query);
    }

    @Override
    public final Set<T> findAll(String username) {
        return findAllDesc(username);
    }
    @Override
    public final Set<T> findAllAsc(String username) {
        String query = getFindAllQuery(username, null, null, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }
    @Override
    public final Set<T> findAllDesc(String username) {
        String query = getFindAllQuery(username, null, null, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final Set<T> findAll(String username, int limit) {
        return findAllDesc(username, limit);
    }
    @Override
    public final Set<T> findAllAsc(String username, int limit) {
        String query = getFindAllQuery(username, null, null, getSortOrderAsc(), limit);
        return executeQuery(query);
    }
    @Override
    public final Set<T> findAllDesc(String username, int limit) {
        String query = getFindAllQuery(username, null, null, getSortOrderDesc(), limit);
        return executeQuery(query);
    }

    @Override
    public final Set<T> findAll(String property, Object value) {
        return findAllDesc(property, value);
    }

    @Override
    public final Set<T> findAllAsc(String property, Object value) {
        String query = getFindAllQuery(null, property, value, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }
    @Override
    public final Set<T> findAllDesc(String property, Object value) {
        String query = getFindAllQuery(null, property, value, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }
    
    @Override
    public final Set<T> findAll(String property, Object value, int limit) {
        return findAllDesc(property, value, limit);
    }

    @Override
    public final Set<T> findAllAsc(String property, Object value, int limit) {
        String query = getFindAllQuery(null, property, value, getSortOrderAsc(), limit);
        return executeQuery(query);
    }
    @Override
    public final Set<T> findAllDesc(String property, Object value, int limit) {
        String query = getFindAllQuery(null, property, value, getSortOrderDesc(), limit);
        return executeQuery(query);
    }
    
    @Override
    public final Set<T> findAll(String username, String property, Object value) {
        return findAllDesc(username, property, value);
    }
    @Override
    public final Set<T> findAllAsc(String username, String property, Object value) {
        String query = getFindAllQuery(username, property, value, getSortOrderAsc(), LIMIT);
        return executeQuery(query);
    }
    @Override
    public final Set<T> findAllDesc(String username, String property, Object value) {
        String query = getFindAllQuery(username, property, value, getSortOrderDesc(), LIMIT);
        return executeQuery(query);
    }

    @Override
    public final Set<T> findAll(String username, String property, Object value, int limit) {
        return findAllDesc(username, property, value, limit);
    }
    @Override
    public final Set<T> findAllAsc(String username, String property, Object value, int limit) {
        String query = getFindAllQuery(username, property, value, getSortOrderAsc(), limit);
        return executeQuery(query);
    }
    @Override
    public final Set<T> findAllDesc(String username, String property, Object value, int limit) {
        String query = getFindAllQuery(username, property, value, getSortOrderDesc(), limit);
        return executeQuery(query);
    }
    
    @Override
    public Set<T> findLatest(){
        return findAllDesc();
    }
    
    @Override
    public Set<T> findOldest(){
        return findAllAsc();
    }

    @Override
    public Set<T> findLatest(int limit){
        return findAllDesc(limit);
    }
    
    @Override
    public Set<T> findOldest(int limit) {
        return findAllAsc(limit);
    }

    @Override
    public Set<T> findLatest(String username) {
        return findAllDesc(username);
    }
    
    @Override
    public Set<T> findOldest(String username) {
        return findAllAsc(username);
    }
    
    @Override
    public Set<T> findLatest(String username, int limit) {
        return findAllDesc(username, limit);
    }
    
    @Override
    public Set<T> findOldest(String username, int limit) {
        return findAllAsc(username, limit);
    }

    @Override
    public Set<T> findAllRead(String username) {
        String query = getMatchStateQuery("READ", username, null, null, LIMIT);
        return executeQuery(query);
    }

    @Override
    public Set<T> findAllFavorite(String username) {
        String query = getMatchStateQuery("FAVORITE", username, null, null, LIMIT);
        return executeQuery(query);
    }

    @Override
    public Set<T> findAllFake(String username) {
        String query = getMatchStateQuery("FAKE", username, null, null, LIMIT);
        return executeQuery(query);
    }
    
    
    @Override
    public Set<T> findAllRead(String username, int limit) {
        String query = getMatchStateQuery("READ", username, null, null, limit);
        return executeQuery(query);
    }

    @Override
    public Set<T> findAllFavorite(String username, int limit) {
        String query = getMatchStateQuery("FAVORITE", username, null, null, limit);
        return executeQuery(query);
    }

    @Override
    public Set<T> findAllFake(String username, int limit) {
        String query = getMatchStateQuery("FAKE", username, null, null, limit);
        return executeQuery(query);
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
        return session.queryForObject(getClassType(), query, new HashMap<>());
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
    public final boolean contains(T object) {
        return find(object) != null;
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
    
    
    private String getCreateStateQuery(String relationName, String userProperty, String userPropertyValue, String entityProperty, String entityPropertyValue) {
            String query = "MATCH (u:FlexUser),(n:" + getClassType().getSimpleName() + ") WHERE\n";
            query += "u." + userProperty   + "=" + FlexUtils.getInstance().wrapUp(userPropertyValue);
            query += " AND ";
            query += "n." + entityProperty + "=" + FlexUtils.getInstance().wrapUp(entityPropertyValue);
            query += " CREATE (u)-[r:" + relationName + "]->(n) RETURN r";
            return query;
    }
    
    private String getMatchStateQuery(String relationName, String username, String property, String value, int limit) {
            String query = "MATCH (u:FlexUser)-[:" + relationName + "]->(n:" + getClassType().getSimpleName() + ") WHERE\n";
            query += "u.username=" + FlexUtils.getInstance().wrapUp(username);
            if(property != null) {
                query += " AND ";
                query += "n." + property + "=" + FlexUtils.getInstance().wrapUp(value);
            }
            query += " RETURN n";
            
            if(limit > 0) {
                query += " limit " + limit;
            }
            return query;
    }

    private String getDeleteStateQuery(String relationName, String userProperty, String userPropertyValue, String entityProperty, String entityPropertyValue) {
            String query = "MATCH (u:FlexUser)-[r:" + relationName + "]->(n:" + getClassType().getSimpleName() + ") WHERE\n";
            query += "u." + userProperty   + "=" + FlexUtils.getInstance().wrapUp(userPropertyValue);
            query += " AND ";
            query += "n." + entityProperty + "=" + FlexUtils.getInstance().wrapUp(entityPropertyValue);
            query += " DELETE r";
            return query;
    }
    private String getFindAllQuery(String username, String property, Object value, SortOrder order, int limit) {
        String query = "MATCH (n:" + getClassType().getSimpleName() + "), (u:FlexUser)  ";
        if(username != null && property != null) {
            query += "WHERE u.username=" + FlexUtils.getInstance().wrapUp(username) + " ";
            query += "AND n." + property + "=" + FlexUtils.getInstance().wrapUp(value.toString()) + " "; 
            query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
            query += "RETURN n ";
        }
        else if(username != null && property == null) {
            query += "WHERE u.username=" + FlexUtils.getInstance().wrapUp(username) + " ";
            query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
            query += "RETURN n ";
        } 
        else if(username == null && property != null) {
            query += "WHERE n." + property + "=" + FlexUtils.getInstance().wrapUp(value.toString()) + " "; 
            query += "AND NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
            query += "RETURN n ";
        }
        else if(username == null && property == null) {
            query += "WHERE NOT ( (u)-[:READ|FAVORITE|FAKE]->(n)) ";
            query += "RETURN n ";
        }
        
        if(order != null) {
            query += order.toString().replace("$", "n") + " ";
        }

        if(limit > 0) {
            query += "LIMIT " + limit;
        }
        
        System.out.println("Query = " + query);
        return query;
    }

    @Override
    public void markAsRead(String username, T entity) {
        if(!isRead(username, entity)){
            getSession().query(getCreateStateQuery("READ", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public void markAsFavorite(String username, T entity) {
        if(!isFavorite(username, entity)) {
            getSession().query(getCreateStateQuery("FAVORITE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public void markAsFake(String username, T entity) {
        if(!isFake(username, entity)) {
            getSession().query(getCreateStateQuery("FAKE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }


    @Override
    public void removeMarkAsRead(String username, T entity) {
        if(isRead(username, entity)) {
            getSession().query(getDeleteStateQuery("READ", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }
    
    @Override
    public void removeMarkAsFavorite(String username, T entity) {
        if(isFavorite(username, entity)) {
            getSession().query(getDeleteStateQuery("FAVORITE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public void removeMarkAsFake(String username, T entity) {
        if(isFake(username, entity)) {
            getSession().query(getDeleteStateQuery("FAKE", "username", username, entity.getPropertyName(), entity.getPropertyValue()), new HashMap<>());
        }
    }

    @Override
    public boolean isRead(String username, T entity) {
        return getSession().queryForObject(getClassType(), getMatchStateQuery("READ", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<>()) 
                != null;
    }

    @Override
    public boolean isFavorite(String username, T entity) {
        return getSession().queryForObject(getClassType(), getMatchStateQuery("FAVORITE", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<>()) 
                != null;
    }

    @Override
    public boolean isFake(String username, T entity) {
        return getSession().queryForObject(getClassType(), getMatchStateQuery("FAKE", username, entity.getPropertyName(), entity.getPropertyValue(), -1), new HashMap<>()) 
                != null;
    }
    
    
    protected Set<T> executeQuery(String query) {
        return Sets.newHashSet(getSession().query(getClassType(), query, new HashMap<>()));
    }
}
