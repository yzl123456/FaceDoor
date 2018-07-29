package estate.dao.impl;

import estate.dao.ConsoleUserDao;
import estate.entity.database.ConsoleUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 应泽林 on 18-1-16.
 *
 */
@Repository("consoleUserDao")
public class ConsoleUserDaoImpl implements ConsoleUserDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public ConsoleUserEntity getConsoleUserByPhone(String phone)
    {
        Session session=getSession();
        String hql="from ConsoleUserEntity t where t.phone=:phone";
        List list=session.createQuery(hql).setString("phone",phone).list();
        if (list.size()>0)
            return (ConsoleUserEntity) list.get(0);
        else
            return null;
    }
}
