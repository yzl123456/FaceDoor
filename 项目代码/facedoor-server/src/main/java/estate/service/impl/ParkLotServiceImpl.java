package estate.service.impl;

import estate.dao.BaseDao;
import estate.dao.ParkLotDao;
import estate.dao.ParkLotOwnerInfoDao;
import estate.entity.database.ParklotOwnerInfoEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.ParkLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("parkLotService")
public class ParkLotServiceImpl implements ParkLotService
{

    @Autowired
    private ParkLotDao parkLotDao;
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private ParkLotOwnerInfoDao parkLotOwnerInfoDao;

    @Override
    public TableData getList(TableFilter tableFilter)
    {
        TableData tableData=parkLotDao.getList(tableFilter);
        tableData.setRecordsTotal(baseDao.count("ParkingLotEntity"));
        return tableData;
    }

    @Override
    public ArrayList<ParklotOwnerInfoEntity> getByParkLotID(Integer id)
    {
        return parkLotOwnerInfoDao.getByParkLotID(id);
    }
}
