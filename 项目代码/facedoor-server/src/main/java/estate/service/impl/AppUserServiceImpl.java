package estate.service.impl;

import estate.dao.AppUserDao;
import estate.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("appUserService")
public class AppUserServiceImpl implements AppUserService
{
    @Autowired
    private AppUserDao appUserDao;

    @Override
    public Object getByPhone(String phone)
    {
        return appUserDao.getByPhone(phone);
    }
}
