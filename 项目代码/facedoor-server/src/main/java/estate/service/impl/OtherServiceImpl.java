package estate.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estate.common.util.Convert;
import estate.dao.BaseDao;
import estate.dao.DoorDao;
import estate.dao.FamilyDao;
import estate.dao.OtherDao;
import estate.dao.PropertyOwnerInfoDao;
import estate.dao.TenantDao;
import estate.dao.UserDao;
import estate.entity.database.DoorEntity;
import estate.entity.database.OtherEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.display.Owner;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.FamilyService;
import estate.service.OtherService;
import estate.service.PropertyService;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("otherService")
public class OtherServiceImpl implements OtherService
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private OtherDao otherDao;
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
	public TableData getOtherList(TableFilter tableFilter) {
		TableData tableData= otherDao.getOtherList(tableFilter);
        ArrayList<OtherEntity> entities= (ArrayList<OtherEntity>) tableData.getJsonString();
 
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
