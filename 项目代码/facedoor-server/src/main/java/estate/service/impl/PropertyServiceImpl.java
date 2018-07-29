package estate.service.impl;

import estate.common.enums.Entity;
import estate.dao.*;
import estate.entity.database.BuildingEntity;
import estate.entity.database.PropertyEntity;
import estate.entity.database.VillageEntity;
import estate.entity.json.Select2;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.EntityTypeErrorException;
import estate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("propertyService")
public class PropertyServiceImpl implements PropertyService
{
    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private VillageDao villageDao;
    @Autowired
    private BuildingDao buildingDao;


    @Override
    public TableData getList(TableFilter tableFilter)
    {
        TableData tableData = propertyDao.getList(tableFilter);
        tableData.setRecordsTotal(baseDao.count("PropertyEntity"));
        return tableData;
    }

    @Override
    public Object getAllVillage()
    {
        ArrayList<Select2> entities = new ArrayList<>();
        ArrayList<VillageEntity> list = villageDao.getAllVillage();
        for (VillageEntity villageEntity : list)
        {
            Select2 select2 = new Select2(String.valueOf(villageEntity.getId()), villageEntity.getName());
            entities.add(select2);
        }
        return entities;
    }

    @Override
    public Object getBuildingsByValliageId(Integer id)
    {
        ArrayList<Select2> entities = new ArrayList<>();
        ArrayList<BuildingEntity> list=buildingDao.getAllBuildingsByVillageId(id);
        if (list==null)
            return null;
        for (BuildingEntity buildingEntity:list)
        {
            Select2 select2=new Select2(String.valueOf(buildingEntity.getId()),buildingEntity.getBuildingName());
            entities.add(select2);
        }
        return entities;
    }

    @Override
    public Object getPropertyByBuildingId(Integer buildingID)
    {
        ArrayList<Select2> entities=new ArrayList<>();
        ArrayList<PropertyEntity> list=propertyDao.getPropertyByBuildingID(buildingID);
        if (list==null)
            return null;
        for (PropertyEntity propertyEntity:list)
        {
            Select2 select2=new Select2(String.valueOf(propertyEntity.getId()),propertyEntity.getLocation());
            entities.add(select2);
        }

        return entities;
    }

    @Override
    public ArrayList<PropertyEntity> getByBuildingID(Integer id)
    {
        return propertyDao.getPropertyByBuildingID(id);
    }


    @Override
    public ArrayList<PropertyEntity> getProperitiesByAppUserPhone(String phone)
    {
//        AppUserEntity appUserEntity;
//        appUserEntity= (AppUserEntity) baseDao.get(phone,AppUserEntity.class);
//        if (appUserEntity==null)
//            return null;
//        int userRole=appUserEntity.getUserRole();
//        return propertyDao.getPropertiesByPhoneRole(phone,userRole);
        return null;
    }

    @Override
    public PropertyEntity getByCode(String code) throws EntityTypeErrorException
    {
        return (PropertyEntity) baseDao.getByCode(code, Entity.PROPERTY);
    }


    @Override
    public ArrayList<PropertyEntity> getPropertyByPhoneRole(String phone, Byte userRole)
    {
        return propertyDao.getPropertiesByPhoneRole(phone, userRole);
    }

    @Override
    public ArrayList<PropertyEntity> getAllPropertyByVillageID(Integer id)
    {
        return propertyDao.getAllProperty();
    }

}
