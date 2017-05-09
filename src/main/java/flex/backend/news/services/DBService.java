package flex.backend.news.services;

/**
 * Created by zua on 15/04/17.
 * @param <T> The type of  managed domain entities
 */
public interface DBService<T> {

    public Iterable<T> findAll();

    public T find(Long id);

    public void delete(Long id);

    public T save(T object);
    
    public long count();

}
