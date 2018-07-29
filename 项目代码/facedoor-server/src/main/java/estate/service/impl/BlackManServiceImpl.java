package estate.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estate.common.util.Convert;
import estate.dao.BaseDao;
import estate.dao.BlackManDao;
import estate.dao.DoorDao;
import estate.dao.FamilyDao;
import estate.dao.PropertyOwnerInfoDao;
import estate.dao.TenantDao;
import estate.dao.UserDao;
import estate.entity.database.BlackManEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.display.Owner;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.BlackManService;
import estate.service.FamilyService;
import estate.service.PropertyService;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("blackManService")
public class BlackManServiceImpl implements BlackManService
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private DoorDao doorDao;
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

    @Autowired
    private BlackManDao blackManDao;
    

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




	@Override
	public TableData getBlackManList(TableFilter tableFilter) {
		
		TableData tableData= blackManDao.getBlackManList(tableFilter);
        ArrayList<BlackManEntity> entities= (ArrayList<BlackManEntity>) tableData.getJsonString();
 
        tableData.setJsonString(entities);
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
