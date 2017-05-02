package flex.backend.news.services;

/**
 * Created by zua on 15/04/17.
 */
public interface DBService<T> {

    public Iterable<T> findAll();

    public T find(Long id);

    public void delete(Long id);

    public void createOrUpdate(T object);

}
