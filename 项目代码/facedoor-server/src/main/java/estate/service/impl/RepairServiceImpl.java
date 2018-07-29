package estate.service.impl;

import estate.dao.BaseDao;
import estate.dao.RepairDao;
import estate.entity.database.RepairEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("repairService")
public class RepairServiceImpl implements RepairService
{
    @Autowired
    private BaseDao baseDao;

    @Autowired
    private RepairDao repairDao;

    public TableData getList(TableFilter tableFilter)
    {
        return repairDao.getList(tableFilter);
    }


    @Override
    public ArrayList<RepairEntity> getRepairByPhone(String phone, Byte status)
    {
        return repairDao.getByPhone(phone, status);
    }
}
