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
import estate.dao.GuestDao;
import estate.dao.PropertyOwnerInfoDao;
import estate.dao.TenantDao;
import estate.dao.UserDao;
import estate.entity.database.AppUserEntity;
import estate.entity.database.DoorEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.database.TenantEntity;
import estate.entity.display.Owner;
import estate.entity.display.Tenant;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.AppUserNotExitException;
import estate.exception.UserTypeErrorException;
import estate.service.DoorService;
import estate.service.FamilyService;
import estate.service.GuestService;
import estate.service.PropertyService;
import estate.service.UserService;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("guestService")
public class GuestServiceImpl implements GuestService
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
    private GuestDao guestDao;

    	

	@Override
	public TableData getGuestList(TableFilter tableFilter) {
		TableData tableData= guestDao.getGuestList(tableFilter);
        ArrayList<DoorEntity> entities= (ArrayList<DoorEntity>) tableData.getJsonString();
 
        tableData.setJsonString(entities);
        return tableData;
	}









}
