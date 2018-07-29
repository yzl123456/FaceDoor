package estate.dao.impl;

import estate.dao.ParkLotOwnerInfoDao;
import estate.entity.database.ParklotOwnerInfoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository("parkLotOwnerInfoDao")
public class ParkLotOwnerInfoDaoImpl implements ParkLotOwnerInfoDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public ArrayList<ParklotOwnerInfoEntity> getByParkLotID(Integer id)
    {
        Session session=getSession();
        String hql="from ParklotOwnerInfoEntity t where t.plId=:id";
        List list=session.createQuery(hql).setInteger("id",id).list();
        if (list.size()>0)
            return (ArrayList<ParklotOwnerInfoEntity>) list;
        return null;
    }

    @Override
    public ArrayList<ParklotOwnerInfoEntity> getByPhone(String phone)
    {
        Session session=getSession();
        String hql="from ParklotOwnerInfoEntity t where t.ownerPhone=:phone";
        List list=session.createQuery(hql).setString("phone",phone).list();
        if (list.size()>0)
            return (ArrayList<ParklotOwnerInfoEntity>) list;
        return null;
    }
}
