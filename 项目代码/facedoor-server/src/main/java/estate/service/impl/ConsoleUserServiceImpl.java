package estate.service.impl;

import estate.dao.ConsoleUserDao;
import estate.entity.database.ConsoleUserEntity;
import estate.service.ConsoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("consoleUserService")
public class ConsoleUserServiceImpl implements ConsoleUserService
{
    @Autowired
    private ConsoleUserDao consoleUserDao;


}
