package estate.dao.impl;

import estate.dao.FamilyDao;
import estate.entity.database.FamilyEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 应泽林 on 18-1-17.
 *
 */
@Repository("familyDao")
public class FamilyDaoImpl implements FamilyDao
{
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public ArrayList<FamilyEntity> getFamilyEntitiesByOwnerId(Integer id)
    {
        Session session=getSession();
        String hql="from FamilyEntity f where f.ownerId=?";
        return (ArrayList<FamilyEntity>) session.createQuery(hql).setInteger(0,id).list();
    }

    @Override
    public ArrayList<FamilyEntity> getFamilByPropertyID(Integer id)
    {
        Session session=getSession();
        String hql="from FamilyEntity t where t.propertyId=:id";

        List list=session.createQuery(hql).setInteger("id",id).list();
        if (list.size()>0)
        {
            return (ArrayList<FamilyEntity>) list;
        }
        return null;
    }
}
