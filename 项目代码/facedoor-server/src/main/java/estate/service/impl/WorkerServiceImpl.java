package estate.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estate.dao.BaseDao;
import estate.dao.DoorDao;
import estate.dao.FamilyDao;
import estate.dao.PropertyOwnerInfoDao;
import estate.dao.TenantDao;
import estate.dao.UserDao;
import estate.dao.WorkerDao;
import estate.entity.database.DoorEntity;
import estate.entity.database.WorkerEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.FamilyService;
import estate.service.PropertyService;
import estate.service.WorkerService;
/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("workerService")
public class WorkerServiceImpl implements WorkerService
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private WorkerDao workerDao;
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


    





	@Override
	public TableData getWorkerList(TableFilter tableFilter) {
		TableData tableData= workerDao.getWorkerList(tableFilter);
        ArrayList<WorkerEntity> entities= (ArrayList<WorkerEntity>) tableData.getJsonString();
 
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
