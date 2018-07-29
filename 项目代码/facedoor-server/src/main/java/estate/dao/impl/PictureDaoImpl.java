package estate.dao.impl;

import estate.dao.PictureDao;
import estate.entity.database.PictureEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 应泽林 on 18-1-16.
 */
@Repository(value = "pictureDao")
public class PictureDaoImpl implements PictureDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }


    public PictureEntity get(Integer id)
    {
        return (PictureEntity)getSession().get(PictureEntity.class, id);
    }
}
