package estate.service.impl;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estate.common.config.UserType;
import estate.common.util.Convert;
import estate.dao.BaseDao;
import estate.dao.DoorDao;
import estate.dao.FamilyDao;
import estate.dao.OwnerVerifyDao;
import estate.dao.PropertyOwnerInfoDao;
import estate.dao.TenantDao;
import estate.dao.UserDao;
import estate.entity.database.AppUserEntity;
import estate.entity.database.DoorEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.database.OwnerVerifyEntity;
import estate.entity.database.TenantEntity;
import estate.entity.display.Owner;
import estate.entity.display.Tenant;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.AppUserNotExitException;
import estate.exception.UserTypeErrorException;
import estate.service.DoorService;
import estate.service.FamilyService;
import estate.service.OwnerVerifyService;
import estate.service.PropertyService;
import estate.service.UserService;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("ownerVerifyService")
public class OwnerVerifyServiceImpl implements OwnerVerifyService
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private OwnerVerifyDao ownerVerifyDao;
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
	public TableData getOwnerVerifyList(TableFilter tableFilter) {
		TableData tableData= ownerVerifyDao.getOwnerVerifyList(tableFilter);
        ArrayList<OwnerVerifyEntity> entities= (ArrayList<OwnerVerifyEntity>) tableData.getJsonString();
 
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
