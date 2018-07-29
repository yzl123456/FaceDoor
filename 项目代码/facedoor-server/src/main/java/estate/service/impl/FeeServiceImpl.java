package estate.service.impl;

import estate.common.Config;
import estate.common.util.GsonUtil;
import estate.common.util.LogUtil;
import estate.dao.*;
import estate.entity.database.FeeItemEntity;
import estate.entity.database.FeeItemOrderEntity;
import estate.entity.database.PropertyEntity;
import estate.entity.database.RuleEntity;
import estate.entity.display.ParkLotFeeInfo;
import estate.entity.json.ParkLotExtra;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("feeService")
public class FeeServiceImpl implements FeeService
{
    @Autowired
    private RuleDao ruleDao;
    @Autowired
    private BaseDao baseDao;

    @Autowired
    private FeeItemDao feeItemDao;
    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private FeeItemOrderDao feeItemOrderDao;

    public void estateFeeAdd(FeeItemEntity feeItemEntity)
    {
        RuleEntity ruleEntity=feeItemEntity.getRuleEntity();
        Integer ruleID=ruleDao.save(ruleEntity);

        feeItemEntity.setRuleId(ruleID);
        feeItemDao.save(feeItemEntity);
    }

    public TableData feeList(TableFilter tableFilter,int feeType)
    {
        if (feeType== Config.PARKING_LOT)
        {
            TableData tableData=feeItemDao.getList(tableFilter,feeType);
            ArrayList<ParkLotFeeInfo> parkLotFeeInfos=new ArrayList<>();
            ArrayList<FeeItemEntity> feeItemEntities= (ArrayList<FeeItemEntity>) tableData.getJsonString();
            for (FeeItemEntity feeItemEntity:feeItemEntities)
            {
                ParkLotFeeInfo parkLotFeeInfo=new ParkLotFeeInfo();
                parkLotFeeInfo.setParkLotExtra(GsonUtil.getGson()
                        .fromJson(feeItemEntity.getDecription(),ParkLotExtra.class));
                parkLotFeeInfo.setName(feeItemEntity.getName());
                parkLotFeeInfo.setRuleEntity(feeItemEntity.getRuleEntity());
                parkLotFeeInfo.setVillageId(feeItemEntity.getVillageId());
                parkLotFeeInfo.setFeeTypeId(feeItemEntity.getFeeTypeId());
                parkLotFeeInfo.setId(feeItemEntity.getId());
                parkLotFeeInfo.setIsPeriodic(feeItemEntity.getIsPeriodic());
                parkLotFeeInfos.add(parkLotFeeInfo);
            }
            tableData.setJsonString(parkLotFeeInfos);
            return tableData;
        }
        else
            return feeItemDao.getList(tableFilter,feeType);
    }

    public void deleteFee(Integer id)
    {
        feeItemDao.delete(id);
    }

    @Override
    public void relateBuilding(ArrayList<Integer> buildingIDs, Integer feeItemID)
    {
//        ArrayList<Integer> propertyIDs =new ArrayList<>();
        feeItemOrderDao.deleteAllByFeeItemID(feeItemID);
        for (Integer buildingID:buildingIDs)
        {
            ArrayList<PropertyEntity> entities =propertyDao.getPropertyByBuildingID(buildingID);
            if (entities.size()>0)
            {
                for (PropertyEntity propertyEntity:entities)
                {
                    FeeItemOrderEntity feeItemOrderEntity=new FeeItemOrderEntity();
                    feeItemOrderEntity.setPropertyId(propertyEntity.getId());
                    feeItemOrderEntity.setFeeItemId(feeItemID);
                    feeItemOrderEntity.setIsBilled(Byte.valueOf("0"));
                    baseDao.save(feeItemOrderEntity);
                    LogUtil.E("propertyID:" + propertyEntity.getId() + "  feeItemID:" + feeItemID);
//                    propertyIDs.add(propertyEntity.getId());
                }
            }
        }
    }

    @Override
    public Object getParkLotFeeByVillageIdType(Integer villageID, String type)
    {
        return feeItemDao.getParkLotByVillageIdType(villageID,type);

    }
}
