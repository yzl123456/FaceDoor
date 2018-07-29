package estate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import estate.common.config.UserType;
import estate.dao.BlackManDao;
import estate.entity.database.AppUserEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository("blackManDao")
public class BlackManDaoImpl implements BlackManDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    /*
     * (non-Javadoc)
     * @see estate.dao.DoorDao#getDoorList(estate.entity.json.TableFilter)
     */
	@Override
	public TableData getBlackManList(TableFilter tableFilter) {
        Session session = getSession();
        TableData tableData = new TableData(true);
        Query query;
        if (!tableFilter.getSearchValue().equals(""))
        {
            String hql = "from BlackManEntity d where d.name like (?)";
            query = session.createQuery(hql).setString(0, "%" + tableFilter.getSearchValue() + "%");
        }
        else
        {
            String hql = "from BlackManEntity d";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        List list=query.setFirstResult(tableFilter.getStart()).setMaxResults(tableFilter.getLength()).list();

        tableData.setRecordsFiltered(count);
        tableData.setJsonString(list);
        tableData.setRecordsTotal(this.count("BlackManEntity"));

        return tableData;
	}
	


    public AppUserEntity getUserByPhone(String phone)
    {
        System.out.print("dsfds");
        return null;
    }

    @Override
    public AppUserEntity getByPhoneStatus(String phone, Byte status)
    {
        Session session=getSession();
        String hql;
        List list;
        if (status!=null)
        {
            hql = "from AppUserEntity t where t.phone=:phone and t.status=:status";
            list = session.createQuery(hql).setString("phone", phone).setByte("status", status).list();
        }
        else
        {
            hql = "from AppUserEntity t where t.phone=:phone";
            list= session.createQuery(hql).setString("phone", phone).list();
        }
        if (list.size()>0)
            return (AppUserEntity) list.get(0);
        return null;
    }

    @Test
    public void test ()
    {
        this.getUserByPhone("18122392");
        System.out.print("dsfds");
    }

    public TableData getOwnerList(TableFilter tableFilter)
    {
        Session session = getSession();
        TableData tableData = new TableData(true);
        Query query;
        if (!tableFilter.getSearchValue().equals(""))
        {
            String hql = "from OwnerEntity o where o.name like (?) or o.phone like (?)";
            query = session.createQuery(hql).setString(0, "%" + tableFilter.getSearchValue() + "%")
                    .setString(1, "%" + tableFilter.getSearchValue() + "%");
        }
        else
        {
            String hql = "from OwnerEntity o";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        List list=query.setFirstResult(tableFilter.getStart()).setMaxResults(tableFilter.getLength()).list();

        tableData.setRecordsFiltered(count);
        tableData.setJsonString(list);
        tableData.setRecordsTotal(this.count("OwnerEntity"));

        return tableData;
    }

    public Integer count(String table)
    {
        Session session=getSession();
        String hql="select count(*) from "+table;
        return ((Long)session.createQuery(hql).uniqueResult()).intValue();
    }


    public TableData getTenantList(TableFilter tableFilter)
    {
        Session session = getSession();
        TableData tableData = new TableData(true);
        Query query;
        if (!tableFilter.getSearchValue().equals(""))
        {
            String hql = "from TenantEntity t where t.name like (?) or t.phone like (?)";
            query = session.createQuery(hql).setString(0, "%" + tableFilter.getSearchValue() + "%")
                    .setString(1, "%" + tableFilter.getSearchValue() + "%");
        }
        else
        {
            String hql = "from TenantEntity t";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        List list=query.setFirstResult(tableFilter.getStart()).setMaxResults(tableFilter.getLength()).list();

        tableData.setRecordsFiltered(count);
        tableData.setJsonString(list);
        tableData.setRecordsTotal(this.count("TenantEntity"));

        return tableData;
    }



    public TableData getAuthenticatedUserList(TableFilter tableFilter)
    {
        Session session = getSession();
        TableData tableData = new TableData(true);
        Query query;
        if (!tableFilter.getSearchValue().equals(""))
        {
            String hql = "from AuthenticatedUserEntity t where t.name like (?)";
            query = session.createQuery(hql).setString(0, "%" + tableFilter.getSearchValue() + "%");
        }
        else
        {
            String hql = "from AuthenticatedUserEntity t";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        List list=query.setFirstResult(tableFilter.getStart()).setMaxResults(tableFilter.getLength()).list();

        tableData.setRecordsFiltered(count);
        tableData.setJsonString(list);
        tableData.setRecordsTotal(this.count("AuthenticatedUserEntity"));

        return tableData;
    }

    @Override
    public TableData getAppUserList(TableFilter tableFilter)
    {
        Session session = getSession();
        TableData tableData = new TableData(true);
        Query query;
        if (!tableFilter.getSearchValue().equals(""))
        {
            String hql = "from AppUserEntity t where t.phone like (?)";
            query = session.createQuery(hql).setString(0, "%" + tableFilter.getSearchValue() + "%");
        }
        else
        {
            String hql = "from AppUserEntity t";
            query = session.createQuery(hql);
        }
        Integer count=query.list().size();
        List list=query.setFirstResult(tableFilter.getStart()).setMaxResults(tableFilter.getLength()).list();

        tableData.setRecordsFiltered(count);
        tableData.setJsonString(list);
        tableData.setRecordsTotal(this.count("AppUserEntity"));

        return tableData;
    }

    @Override
    public Object getUserInfoByPhoneRole(String phone, int type)
    {
        Session session=getSession();
        String hql;
        if (type== UserType.TENANT)
            hql="from TenantEntity t where t.phone=:phone";
        else if (type==UserType.FAMILY)
            hql="from FamilyEntity t where t.phone=:phone";
        else if (type==UserType.APPUSER)
            hql="from AppUserEntity t where t.phone=:phone";
        else if (type==UserType.OWNER)
            hql="from OwnerEntity t where t.phone=:phone";
        else
            return null;
        List list=session.createQuery(hql).setString("phone",phone).list();
        if (list.size()>0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public void deleteUserByPhone(String phone, byte type)
    {
        Session session=getSession();
        String hql;
        if (type==UserType.OWNER)
            hql="delete from OwnerEntity t where t.phone=:phone";
        else if (type==UserType.APPUSER)
            hql="delete from AppUserEntity t where t.phone=:phone";
        else if (type==UserType.TENANT)
            hql="delete from TenantEntity t where t.phone=:phone";
        else if (type==UserType.FAMILY)
            hql="delete from FamilyEntity t where t.phone=:phone";
        else
            return;
        session.createQuery(hql).setString("phone",phone).executeUpdate();
    }

    @Override
    public ArrayList<Object> getOwnersByPropertyID(Integer id)
    {
        Session session=getSession();
        String hql="select o from PropertyOwnerInfoEntity t ,OwnerEntity o where t.propertyId=:id and t.ownerPhone=o.phone";
        List list=session.createQuery(hql).setInteger("id",id).list();
        return (ArrayList<Object>) list;
    }

    @Override
    public ArrayList<Object> getAppUserByPropertyID(Integer id)
    {
        Session session=getSession();
        String hql="from ";
        session.createQuery(hql).list();
        return null;
    }

    @Override
    public ArrayList<AppUserEntity> getAllAppUser()
    {
        Session session =getSession();
        String hql="from AppUserEntity ";
        List list=session.createQuery(hql).list();
        if (list.size()>0)
            return (ArrayList<AppUserEntity>) list;
        return null;
    }




}
