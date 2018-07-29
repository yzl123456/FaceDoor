package estate.dao.impl;

import estate.dao.AppUserDao;
import estate.entity.database.AppUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 应泽林 on 18-1-27.
 *
 */
@Repository("appUserDao")
public class AppUserDaoImpl implements AppUserDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public AppUserEntity getByPhone(String phone)
    {
        Session session=getSession();
        String hql="from AppUserEntity t where t.phone=:phone";
        List list=session.createQuery(hql).setString("phone",phone).list();
        if (list.size()>0)
            return (AppUserEntity) list.get(0);
        else
            return null;
    }
}
