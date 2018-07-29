package estate.dao.impl;

import estate.dao.ComplainDao;
import estate.entity.database.ComplainEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository("complainDao")
public class ComplainDaoImpl implements ComplainDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public TableData getList(TableFilter tableFilter)
    {
        Session session = getSession();
        TableData tableData = new TableData(true);
        Query query;
        if (!tableFilter.getSearchValue().equals(""))
        {
            String hql = "from ComplainEntity t where t.title like (?)";
            query = session.createQuery(hql).setString(0, "%" + tableFilter.getSearchValue() + "%");
        }
        else
        {
            String hql = "from ComplainEntity t";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        List list=query.setFirstResult(tableFilter.getStart()).setMaxResults(tableFilter.getLength()).list();

        tableData.setRecordsFiltered(count);
        tableData.setJsonString(list);
        tableData.setRecordsTotal(this.count("ComplainEntity"));

        return tableData;
    }

    @Override
    public ArrayList<ComplainEntity> getByPhone(String phone,Byte status)
    {
        Session session=getSession();
        String hql="";
        List list;
        if (status==null)
        {
            hql = "from ComplainEntity t where t.phone=:phone";
            list = session.createQuery(hql).setString("phone", phone).list();
        }
        else
        {
            hql = "from ComplainEntity t where t.phone=:phone and t.status=:status";
            list= session.createQuery(hql).setString("phone", phone).setByte("status",status).list();
        }
        if (list.size()>0)
            return (ArrayList<ComplainEntity>) list;
        return null;
    }


    private Integer count(String table)
    {
        Session session=getSession();
        String hql="select count(*) from "+table;
        return ((Long)session.createQuery(hql).uniqueResult()).intValue();
    }

}
