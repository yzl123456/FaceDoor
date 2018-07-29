package estate.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estate.common.util.Convert;
import estate.dao.BaseDao;
import estate.dao.DoorDao;
import estate.dao.DoorRecordDao;
import estate.dao.FamilyDao;
import estate.dao.PropertyOwnerInfoDao;
import estate.dao.TenantDao;
import estate.dao.UserDao;
import estate.entity.database.DoorRecordEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.display.DoorRecord;
import estate.entity.display.Owner;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.DoorRecordService;
import estate.service.FamilyService;
import estate.service.PropertyService;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("doorRecordService")
public class DoorRecordServiceImpl implements DoorRecordService
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private DoorRecordDao doorRecordDao;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private PropertyOwnerInfoDao propertyOwnerInfoDao;
    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private FamilyDao familyDao;

	@Override
	public TableData getDoorRecordList(TableFilter tableFilter) {
		TableData tableData= doorRecordDao.getDoorRecordList(tableFilter);
        ArrayList<DoorRecordEntity> entities= (ArrayList<DoorRecordEntity>) tableData.getJsonString();
        
        ArrayList<DoorRecord> records= new ArrayList<>();
        for (DoorRecordEntity doorRecordEntity:entities)
        {	
        	DoorRecord record=new DoorRecord();
        	record.setId(doorRecordEntity.getId());

        	record.setFaceURL(doorRecordEntity.getFaceURL());
        	record.setUserName(doorRecordEntity.getName());

        	record.setDoorLocation(doorRecordEntity.getLocation());
        	record.setRole(doorRecordEntity.getRole());
        	record.setStatus(doorRecordEntity.getInOrOut());
        	record.setOpenWay(doorRecordEntity.getOpenway());
        	record.setDoTime(Convert.num2time(doorRecordEntity.getTime(),"yyyy-MM-dd HH:mm:ss"));
        	
        	records.add(record);

        }
        tableData.setJsonString(records);
        return tableData;
	}
    

    public TableData getOwnerList(TableFilter tableFilter)
    {
        TableData tableData=userDao.getOwnerList(tableFilter);
        ArrayList<OwnerEntity> entities= (ArrayList<OwnerEntity>) tableData.getJsonString();
        ArrayList<Owner> owners= new ArrayList<>();
        for (OwnerEntity ownerEntity:entities)
        {
            Owner owner=new Owner();
            owner.setOwnerId(ownerEntity.getId());
            owner.setName(ownerEntity.getName());
            owner.setPhone(ownerEntity.getPhone());
            owner.setIdentityCode(ownerEntity.getIdentityCode());
            owner.setPropertyIdList(ownerEntity.getPropertyIdList());
            owner.setVehicleIdIst(ownerEntity.getVehicleIdIst());
            owner.setUrgentName(ownerEntity.getUrgentName());
            owner.setUrgentPhone(ownerEntity.getUrgentPhone());

            owner.setSex(Convert.num2sex(ownerEntity.getSex()));
            owner.setIdentityType(Convert.num2idtype(ownerEntity.getIdentityType()));
            owner.setAuthenticationTime(Convert.num2time(ownerEntity.getAuthenticationTime()));
            owner.setBirthday(Convert.num2time(ownerEntity.getBirthday()));

            owner.setFamilies(familyService.getFamiliesByOwnerID(ownerEntity.getId()));
            String baseURL="img//face//owner//";
            owner.setFaceURL(baseURL+ownerEntity.getFaceURL());
//            owner.setPropertyEntities(propertyService.getPropertiesByString(ownerEntity.getPropertyIdList()));
            owners.add(owner);

        }
        tableData.setJsonString(owners);
        return tableData;
    }












//    @Override
//    public void changeAppUserStatus(AppUserEntity appUserEntity)
//    {
//        AppUserEntity appUserEntity1;
//        appUserEntity1=(AppUserEntity)baseDao.get(appUserEntity.getPhone(), appUserEntity);
////        appUserEntity1.setStatus(appUserEntity.getStatus());
//        baseDao.save(appUserEntity1);
//    }







}
