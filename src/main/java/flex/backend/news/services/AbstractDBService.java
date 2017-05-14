/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.GraphEntity;
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
        return session.loadAll(getClassType(), 2);
    }

    @Override
    public T find(Long id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.load(getClassType(), id, 2);
    }

    @Override
    public void delete(Long id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.delete(find(id));
    }

    @Override
    public T save(T object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try {
            session.save(object);
        } catch(Exception e) {
            return object;
        }
        return find(session.resolveGraphIdFor(object));
    }
    
    @Override
    public long count() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.countEntitiesOfType(getClassType());
    }
    
    protected abstract Class<T> getClassType();
}
