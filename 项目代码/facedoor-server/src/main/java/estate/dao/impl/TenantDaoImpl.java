package estate.dao.impl;

import estate.dao.TenantDao;
import estate.entity.database.TenantEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository("tenantDao")
public class TenantDaoImpl implements TenantDao
{
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ArrayList<TenantEntity> getTenantByPropertyID(Integer id)
    {
        Session session=getSession();
        String hql="from TenantEntity t where t.propertyId=:id";

        List list=session.createQuery(hql).setInteger("id",id).list();
        if (list.size()>0)
            return (ArrayList<TenantEntity>) list;
        return null;
    }
}
