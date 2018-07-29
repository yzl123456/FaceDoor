package estate.dao.impl;

import estate.common.enums.Entity;
import estate.dao.BaseDao;
import estate.exception.EntityTypeErrorException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 应泽林 on 18-1-20.
 *
 */
@Repository("baseDao")
public class BaseDaoImpl implements BaseDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(Object object)
    {
        Session session=getSession();
        session.saveOrUpdate(object);
        session.flush();
        session.evict(object);
//        Class c=object.getClass();
//        Method[] methods=c.getMethods();
//        for (Method method:methods)
//        {
//            if (method.getName().matches("^getId$"))
//            {
//                try
//                {
//                    return (Integer)method.invoke(object);
//                }
//                catch (IllegalAccessException | InvocationTargetException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
    }



    @Override
    public Object get(Serializable id,Object object)
    {
        return getSession().get(object.getClass(),id);
    }

    @Override
    public Object get(Serializable id, Class cls)
    {
        Object o=getSession().get(cls,id);
        if (o!=null)
            return getSession().get(cls,id);
        else
            return null;
    }

    @Override
    public Object getAll(Class cls)
    {
        Session session=getSession();

        String entity=cls.getSimpleName();
        String hql="from "+entity+" t";
        List list=session.createQuery(hql).list();
        if (list.size()>0)
            return list;
        return null;
    }

    @Override
    public void delete(Object object)
    {
        Session session=getSession();
        session.delete(object);
    }

    @Override
    public Object getByCode(String code, Entity entity) throws EntityTypeErrorException
    {
        Session session=getSession();
        String hql="";
        switch (entity)
        {
            case PROPERTY:
                hql="from PropertyEntity t where t.code=:code";
                break;
            case BUILDING:
                hql="from BuildingEntity t where t.buildingCode=:code";
                break;
            default:
                throw new EntityTypeErrorException("该对象不存在!");
        }

        List list=session.createQuery(hql).setString("code",code).list();
        if (list.size()>0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public Integer count(String table)
    {
        Session session=getSession();
        String hql="select count(*) from "+table;
        return ((Long)session.createQuery(hql).uniqueResult()).intValue();
    }

}
