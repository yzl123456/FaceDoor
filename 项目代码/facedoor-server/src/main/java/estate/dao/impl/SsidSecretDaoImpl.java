package estate.dao.impl;

import estate.dao.SsidSecretDao;
import estate.entity.database.SsidSecretEntity;
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
@Repository("ssidSecretDao")
public class SsidSecretDaoImpl implements SsidSecretDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public SsidSecretEntity getBySymbol(String symbol)
    {
        Session session=getSession();
        String hql="from SsidSecretEntity s where s.symbol=:symbol";
        List list=session.createQuery(hql).setString("symbol",symbol).list();
        if (list.size()>0)
            return (SsidSecretEntity)list.get(0);
        else
            return null;
    }

    @Override
    public TableData getList(TableFilter tableFilter)
    {
        Session session = getSession();
        TableData tableData = new TableData(true);
        ArrayList<SsidSecretEntity> entities=new ArrayList<>();
        Query query;
        if (!tableFilter.getSearchValue().equals(""))
        {
            String hql = "from SsidSecretEntity t ,BuildingEntity b where t.ssid like (?) and b.id=t.buildingId";
            query = session.createQuery(hql).setString(0, "%" + tableFilter.getSearchValue() + "%");
        }
        else
        {
            String hql = "from SsidSecretEntity t,BuildingEntity b where b.id=t.buildingId";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        List list=query.setFirstResult(tableFilter.getStart()).setMaxResults(tableFilter.getLength()).list();
        for (Object aList : list)
        {
            Object[] objects = (Object[]) aList;
            SsidSecretEntity ssidSecretEntity=(SsidSecretEntity) objects[0];
            entities.add(ssidSecretEntity);
        }

        tableData.setRecordsFiltered(count);
        tableData.setJsonString(entities);
        return tableData;
    }
}
