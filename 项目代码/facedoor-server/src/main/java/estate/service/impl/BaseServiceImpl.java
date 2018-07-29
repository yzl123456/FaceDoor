package estate.service.impl;

import estate.dao.BaseDao;
import estate.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("baseService")
public class BaseServiceImpl implements BaseService
{
    @Autowired
    private BaseDao baseDao;

    @Override
    public void save(Object object)
    {
        baseDao.save(object);
    }

    @Override
    public Object get(Integer id, Object object)
    {
        return baseDao.get(id,object);
    }

    @Override
    public Object get(Serializable id, Class cls)
    {
        return baseDao.get(id,cls);
    }

    @Override
    public Object get(Integer id, Class cls)
    {
        return baseDao.get(id,cls);
    }

    @Override
    public Object getAll(Class cls)
    {
        return baseDao.getAll(cls);
    }

    @Override
    public void delete(Object object)
    {
        baseDao.delete(object);
    }
}
