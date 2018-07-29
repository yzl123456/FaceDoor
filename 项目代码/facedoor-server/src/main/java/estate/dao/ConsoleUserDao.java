package estate.dao;

import estate.entity.database.ConsoleUserEntity;

/**
 * Created by 应泽林 on 18-1-16.
 */
public interface ConsoleUserDao
{
    ConsoleUserEntity getConsoleUserByPhone(String phone);


}
