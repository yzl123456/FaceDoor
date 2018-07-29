package estate.dao.impl;

import estate.dao.ParkLotDao;
import estate.entity.database.ParkingLotEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository("parkLotDao")
public class ParkLotDaoImpl implements ParkLotDao
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
        ArrayList<ParkingLotEntity> entities;
        Query query;

        if (tableFilter.getSearchValue()!=null)
        {
            String hql="from ParkingLotEntity t where t.code like (?)";
            query=session.createQuery(hql).setString(0,"%"+tableFilter.getSearchValue()+"%");
        }
        else
        {
            String hql = "from ParkingLotEntity t";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        entities=(ArrayList<ParkingLotEntity>)query
                .setFirstResult(tableFilter.getStart())
                .setMaxResults(tableFilter.getLength())
                .list();
        tableData.setRecordsFiltered(count);
        tableData.setJsonString(entities);
        return tableData;
    }
}
