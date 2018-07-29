package estate.dao.impl;

import estate.dao.BrakeDao;
import estate.entity.database.BrakeEntity;
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
 * Created by 应泽林 on 18-1-15.
 *
 */
@Repository("brakeDao")
public class BrakeDaoImpl implements BrakeDao
{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public TableData getList(TableFilter tableFilter)
    {
        Session session=getSession();
        TableData tableData=new TableData();
        ArrayList<BrakeEntity> entities;
        Query query;

        if (tableFilter.getSearchValue()!=null)
        {
            String hql="from BrakeEntity t where t.code like (?)";
            query=session.createQuery(hql).setString(0,"%"+tableFilter.getSearchValue()+"%");
        }
        else
        {
            String hql = "from BrakeEntity t";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        entities=(ArrayList<BrakeEntity>)query
                .setFirstResult(tableFilter.getStart())
                .setMaxResults(tableFilter.getLength())
                .list();
        tableData.setRecordsFiltered(count);
        tableData.setJsonString(entities);
        return tableData;
    }

    @Override
    public ArrayList<BrakeEntity> getByVillageID(Integer id)
    {
        Session session=getSession();
        String hql="from BrakeEntity t where t.villageId=:id";
        List list=session.createQuery(hql).setInteger("id",id).list();
        if (list.size()>0)
            return (ArrayList<BrakeEntity>) list;
        return null;
    }
}
