package services.news;

import org.neo4j.ogm.cypher.query.SortOrder;

/**
 * Created by zua on 15/04/17.
 * @param <T> The type of  managed domain entities
 */
public interface DBService<T> {

    public Iterable<T> findAll();
    public Iterable<T> findAll(int limit);
    public Iterable<T> findAll(String property, Object value);
    public Iterable<T> findAll(String property, Object value, int limit);
    
    public boolean contains(T object);

    public T find(Long id);
    
    public T find(T object);

    public void delete(Long id);

    public T save(T object);
    
    public T update(T object);
    public T update(T dbObject, T object);
    
    public long count();
    
    public Class<T> getClassType();
    public SortOrder getSortOrder();


}
