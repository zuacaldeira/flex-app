package services.news;

import org.neo4j.ogm.cypher.query.SortOrder;

/**
 * Created by zua on 15/04/17.
 * @param <T> The type of  managed domain entities
 */
public interface DBService<T> {

    public Iterable<T> findAll();
    public Iterable<T> findAllAsc();
    public Iterable<T> findAllDesc();

    public Iterable<T> findAll(String property, Object value);
    public Iterable<T> findAllAsc(String property, Object value);
    public Iterable<T> findAllDesc(String property, Object value);

    public Iterable<T> findAll(String username);
    public Iterable<T> findAllAsc(String username);
    public Iterable<T> findAllDesc(String username);

    public Iterable<T> findAll(String username, String property, Object value);
    public Iterable<T> findAllAsc(String username, String property, Object value);
    public Iterable<T> findAllDesc(String username, String property, Object value);
    
    public Iterable<T> findAllRead(String username);
    public Iterable<T> findAllFavorite(String username);
    public Iterable<T> findAllFake(String username);
    
    public boolean isRead(String username, T entity);
    public boolean isFavorite(String username, T entity);
    public boolean isFake(String username, T entity);

    public void markAsRead(String username, T entity);
    public void markAsFavorite(String username, T entity);
    public void markAsFake(String username, T entity);

    public void removeMarkAsRead(String username, T entity);
    public void removeMarkAsFavorite(String username, T entity);
    public void removeMarkAsFake(String username, T entity);
    
    public T find(Long id);    
    public T find(T object);

    public T save(T object);

    public T update(T object);
    public T update(T dbObject, T object);    

    public void delete(Long id);

    public boolean contains(T object);
    public long count();
    
    public Class<T> getClassType();
    public SortOrder getSortOrderAsc();
    public SortOrder getSortOrderDesc();
}
