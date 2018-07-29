package estate.service.impl;

import estate.dao.BaseDao;
import estate.dao.BuildingDao;
import estate.entity.database.BuildingEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("buildingService")
public class BuildingServiceImpl implements BuildingService
{
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private BuildingDao buildingDao;

    @Override
    public TableData getList(TableFilter tableFilter)
    {
        TableData tableData=buildingDao.getList(tableFilter);
        tableData.setRecordsTotal(baseDao.count("BuildingEntity"));
        return tableData;
    }

    @Override
    public Integer getIDByCode(String code)
    {
        BuildingEntity buildingEntity=buildingDao.getByCode(code);
        if (buildingEntity!=null)
        {
            return buildingEntity.getId();
        }
        else
        {
            return null;
        }
    }

    @Override
    public ArrayList<BuildingEntity> getByVillageID(Integer id)
    {
        return buildingDao.getAllBuildingsByVillageId(id);
    }
}
