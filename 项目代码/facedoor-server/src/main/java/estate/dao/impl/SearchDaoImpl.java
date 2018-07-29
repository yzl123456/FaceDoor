package estate.dao.impl;

import estate.dao.SearchDao;
import estate.entity.database.VillageEntity;
import estate.entity.json.Select2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository("searchDao")
public class SearchDaoImpl implements SearchDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public ArrayList<VillageEntity> villageSearch(String name)
    {
        Session session=getSession();
        ArrayList<VillageEntity> entities=new ArrayList<>();
        String hql="select v.name as name,v.id as villageId from VillageEntity v where v.name like (:name)";
        List list=session.createQuery(hql).setString("name","%"+name+"%").list();
        for (Object object:list)
        {
            Object[] objects=(Object[])object;
            VillageEntity villageEntity=new VillageEntity();
            villageEntity.setId((Integer)objects[1]);
            villageEntity.setName((String)objects[0]);
            entities.add(villageEntity);
        }
        return entities;
    }

    @Override
    public ArrayList<Select2> ownerSearch(String phone)
    {
        Session session=getSession();
        ArrayList<Select2> entities=new ArrayList<>();
        String hql="select o.phone, o.name from OwnerEntity o where o.phone like (:phone) or o.name like (:name)";
        List list=session.createQuery(hql).setString("phone", "%" + phone + "%").setString("name","%"+phone+"%").list();
        for (Object object:list)
        {
            Object[] objects=(Object[])object;
            Select2 select2=new Select2((String)objects[0], objects[1]+"-"+objects[0]);
            entities.add(select2);
        }
        return entities;
    }
}
