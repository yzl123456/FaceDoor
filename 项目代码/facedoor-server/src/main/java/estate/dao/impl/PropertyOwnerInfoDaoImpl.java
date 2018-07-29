package estate.dao.impl;

import estate.dao.PropertyOwnerInfoDao;
import estate.entity.database.PropertyOwnerInfoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository("propertyOwnerInfoDao")
public class PropertyOwnerInfoDaoImpl implements PropertyOwnerInfoDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void deleteByPhonePropertyID(String phone, Integer id,Byte role)
    {
        Session session=getSession();
        String hql="delete from PropertyOwnerInfoEntity t where t.propertyId=:id and t.phone=:phone and t" +
                ".userRole=:role";
        session.createQuery(hql).setInteger("id",id).setString("phone",phone).setByte("role",role).executeUpdate();
    }

    @Override
    public ArrayList<PropertyOwnerInfoEntity> getByPhone(String phone)
    {
        Session session=getSession();
        String hql="from  PropertyOwnerInfoEntity t where t.phone=:phone";
        List list=session.createQuery(hql).setString("phone",phone).list();
        if (list.size()>0)
            return (ArrayList<PropertyOwnerInfoEntity>) list;
        return null;
    }

    @Override
    public ArrayList<PropertyOwnerInfoEntity> getByPhoneProperID(String phone, Integer propertyID)
    {
        Session session=getSession();
        String hql="from PropertyOwnerInfoEntity t where t.phone=:phone and t.propertyId=:id";
        List list=session.createQuery(hql).setString("phone",phone).setInteger("id",propertyID).list();
        if (list.size()>0)
            return (ArrayList<PropertyOwnerInfoEntity>) list;
        return null;
    }

    @Override
    public ArrayList<PropertyOwnerInfoEntity> getBindBypropertyIDStatus(Integer propertyID, Byte status)
    {
        Session session=getSession();
        String hql;
        List list;
        if (status==null)
        {
            hql = "from  PropertyOwnerInfoEntity t where t.propertyId=:propertyID and t.userRole!=3";
            list = session.createQuery(hql).setInteger("propertyID", propertyID).list();
        }
        else
        {
            hql = "from  PropertyOwnerInfoEntity t where t.propertyId=:propertyID and t.status=:status and t.userRole!=3";
            list = session.createQuery(hql).setInteger("propertyID", propertyID).setByte("status", status).list();
        }
        if (list.size()>0)
            return (ArrayList<PropertyOwnerInfoEntity>) list;
        return null;
    }

    @Override
    public ArrayList<PropertyOwnerInfoEntity> getByPhonePropertyID(String phone, Integer propertyID)
    {
        Session session=getSession();
        String hql="from PropertyOwnerInfoEntity t where t.propertyId=:propertyID and t.phone=:phone";
        List list=session.createQuery(hql).setInteger("propertyID",propertyID).setString("phone",phone).list();
        if (list.size()>0)
            return (ArrayList<PropertyOwnerInfoEntity>) list;
        return null;
    }

    @Override
    public ArrayList<PropertyOwnerInfoEntity> getByPropertyIdRole(Integer propertyID, Byte role)
    {
        Session session=getSession();
        String hql;
        List list;
        if (role==null)
        {
            hql = "from PropertyOwnerInfoEntity t where t.propertyId=:id";
            list = session.createQuery(hql).setInteger("id", propertyID).list();
        }
        else
        {
            hql = "from PropertyOwnerInfoEntity t where t.propertyId=:id and t.userRole=:role";
            list = session.createQuery(hql).setInteger("id", propertyID).setByte("role", role).list();
        }
        if (list.size()>0)
            return (ArrayList<PropertyOwnerInfoEntity>) list;
        return null;
    }

}
