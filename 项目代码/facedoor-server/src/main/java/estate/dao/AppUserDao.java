package estate.dao;

import estate.entity.database.AppUserEntity;

/**
 * Created by 应泽林 on 18-1-27.
 */
public interface AppUserDao
{
    AppUserEntity getByPhone(String phone);
}
