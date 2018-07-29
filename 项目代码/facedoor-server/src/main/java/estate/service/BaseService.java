package estate.service;

import java.io.Serializable;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface BaseService
{
    void save(Object object);

    Object get(Integer id,Object object);

    Object get(Serializable id,Class cls);

    Object get(Integer id,Class object);


    Object getAll(Class cls);

    void delete(Object object);

}
