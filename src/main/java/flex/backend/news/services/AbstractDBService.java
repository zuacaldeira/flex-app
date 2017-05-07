/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.GraphEntity;
import java.util.HashMap;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class AbstractDBService<T extends GraphEntity> implements DBService<T> {

    @Override
    public Iterable<T> findAll() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.loadAll(getClassType());
    }

    @Override
    public T find(Long id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.load(getClassType(), id);
    }

    @Override
    public void delete(Long id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.delete(session.load(getClassType(), id));
    }

    @Override
    public T save(T object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        T dbObject = session.queryForObject(getClassType(), Neo4jQueries.findQuery(object), new HashMap<>());
        if(dbObject != null) {
            session.delete(dbObject);
        }
        session.save(object);
        return session.queryForObject(getClassType(), Neo4jQueries.findQuery(object), new HashMap<>());
    }
    
    protected abstract Class<T> getClassType();
    protected abstract String getPropertyName();
    protected abstract String getPropertyValue(T object);
}
