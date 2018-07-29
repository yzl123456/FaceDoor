package estate.dao.impl;

import estate.dao.RuleDao;
import estate.entity.database.RuleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository("ruleDao")
public class RuleDaoImpl implements RuleDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public Integer save(RuleEntity ruleEntity)
    {
        Session session=getSession();
        session.saveOrUpdate(ruleEntity);
        return ruleEntity.getRuleId();
    }

    public void delete(Integer ruleID)
    {

    }

    public RuleEntity get(Integer ruleID)
    {
        return (RuleEntity) getSession().get(RuleEntity.class,ruleID);
    }
}
