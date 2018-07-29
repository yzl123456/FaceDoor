package estate.dao;

import estate.common.enums.Entity;
import estate.exception.EntityTypeErrorException;

import java.io.Serializable;

/**
 * Created by 应泽林 on 18-1-16.
 * 定义每个DAO具有的基础的增删改查操作
 */
public interface BaseDao
{

    /**
     * 包含基础的增加和更新操作,适用于单个对象
     * @param object
     * @return
     */
    void save(Object object);

    /**
     * 根据ID获取某个对象
     * @param id
     * @return
     */
    Object get(Serializable id,Object object);
    Object get(Serializable id,Class object);

    Object getAll(Class cls);

    /**
     * 根据对象信息删除某个对象
     * @param object
     */
    void delete(Object object);


    /**
     * 通过代码和对象名获取对象
     * @param code
     * @param entity
     * @return
     */
    Object getByCode(String code,Entity entity) throws EntityTypeErrorException;

    /**
     * 根据表名计算总数
     * @param table
     * @return
     */
    Integer count(String table);

}
