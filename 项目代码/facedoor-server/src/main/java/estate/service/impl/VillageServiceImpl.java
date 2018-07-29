package estate.service.impl;

import estate.dao.BaseDao;
import estate.dao.VillageDao;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("villageService")
public class VillageServiceImpl implements VillageService
{

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private VillageDao villageDao;

    @Override
    public TableData getList(TableFilter tableFilter)
    {
        TableData tableData=villageDao.getList(tableFilter);
        tableData.setRecordsTotal(baseDao.count("VillageEntity"));
        return tableData;
    }
}
