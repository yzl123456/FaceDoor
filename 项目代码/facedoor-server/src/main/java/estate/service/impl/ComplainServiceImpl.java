package estate.service.impl;

import estate.dao.ComplainDao;
import estate.entity.database.ComplainEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.ComplainService;
import estate.service.ConsoleUserService;
import estate.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("complainService")
public class ComplainServiceImpl implements ComplainService
{
    @Autowired
    private ComplainDao complainDao;
    @Autowired
    private ConsoleUserService consoleUserService;
    @Autowired
    private PictureService pictureService;

    public TableData getList(TableFilter tableFilter)
    {
        return complainDao.getList(tableFilter);
    }

    public void dealComplain(ComplainEntity complainEntity)
    {

    }

    @Override
    public ArrayList<ComplainEntity> getComplainByPhone(String phone,Byte status)
    {
        return complainDao.getByPhone(phone,status);
    }
}
