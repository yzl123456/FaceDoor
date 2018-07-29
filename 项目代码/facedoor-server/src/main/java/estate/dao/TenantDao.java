package estate.dao;

import estate.entity.database.TenantEntity;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-9.
 *
 */
public interface TenantDao
{
    ArrayList<TenantEntity> getTenantByPropertyID(Integer id);
}
