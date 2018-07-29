package estate.dao.impl;

import estate.dao.BillDao;
import estate.entity.database.BillEntity;
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
 * Created by 应泽林 on 18-1-6.
 *
 */
@Repository("billDao")
public class BillDaoImpl implements BillDao
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
        ArrayList<BillEntity> entities;
        Query query;

        StringBuffer hql=new StringBuffer("from BillEntity t ");
        if (tableFilter.getSearchValue()!=null)
        {
            hql.append("where t.feeItemFee like (:searchValue) ");
            if (tableFilter.getStatus()!=null)
            {
                hql.append("and t.payStatus=:status ");
                if (tableFilter.getStartTime()!=null&&tableFilter.getEndTime()!=null)
                {
                    hql.append("and t.billGenerationTime>=:startTime and t.billGenerationTime<=:endTime ");
                    query=session.createQuery(hql.toString())
                            .setString("searchValue", "%" + tableFilter.getSearchValue() + "%")
                            .setByte("status",tableFilter.getStatus())
                            .setLong("startTime", tableFilter.getStartTime())
                            .setLong("endTime", tableFilter.getEndTime());
                }
                else
                {
                    query=session.createQuery(hql.toString())
                            .setString("searchValue", "%" + tableFilter.getSearchValue() + "%")
                            .setByte("status", tableFilter.getStatus());
                }
            }
            else
            {
                if (tableFilter.getStartTime()!=null&&tableFilter.getEndTime()!=null)
                {
                    hql.append("and t.billGenerationTime>=:startTime and t.billGenerationTime<=:endTime ");
                    query=session.createQuery(hql.toString())
                            .setString("searchValue", "%" + tableFilter.getSearchValue() + "%")
                            .setLong("startTime", tableFilter.getStartTime())
                            .setLong("endTime", tableFilter.getEndTime());
                }
                else
                {
                    query=session.createQuery(hql.toString())
                            .setString("searchValue", "%" + tableFilter.getSearchValue() + "%");
                }
            }
        }
        else
        {
            if (tableFilter.getStatus()!=null)
            {
                hql.append("where t.payStatus=:status ");
                if (tableFilter.getStartTime()!=null&&tableFilter.getEndTime()!=null)
                {
                    hql.append("and t.billGenerationTime>=:startTime and t.billGenerationTime<=:endTime ");
                    query=session.createQuery(hql.toString())
                            .setByte("status",tableFilter.getStatus())
                            .setLong("startTime", tableFilter.getStartTime())
                            .setLong("endTime", tableFilter.getEndTime());
                }
                else
                {
                    query=session.createQuery(hql.toString())
                            .setByte("status", tableFilter.getStatus());
                }
            }
            else
            {
                if (tableFilter.getStartTime()!=null&&tableFilter.getEndTime()!=null)
                {
                    hql.append("where t.billGenerationTime>=:startTime and t.billGenerationTime<=:endTime ");
                    query=session.createQuery(hql.toString())
                            .setLong("startTime", tableFilter.getStartTime())
                            .setLong("endTime", tableFilter.getEndTime());
                }
                else
                {
                    query=session.createQuery(hql.toString());
                }
            }
        }
        Integer count=query.list().size();
        entities=(ArrayList<BillEntity>)query.setFirstResult(tableFilter.getStart()).setMaxResults(tableFilter
                .getLength()).list();
        tableData.setRecordsFiltered(count);
        tableData.setJsonString(entities);
        return tableData;
    }

    @Override
    public ArrayList<BillEntity> getByPropertyID(Integer id,Byte status)
    {
        Session session=getSession();
        List list;
        if (status!=null)
        {
            String hql="from BillEntity t where t.propertyId=:id and t.payStatus=:status";
            list=session.createQuery(hql).setInteger("id", id).setByte("status",status).list();
        }
        else
        {
            String hql="from BillEntity t where t.propertyId=:id";
            list=session.createQuery(hql).setInteger("id", id).list();
        }
        return (ArrayList<BillEntity>) list;
    }
}
