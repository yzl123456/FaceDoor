package estate.service.impl;

import com.google.gson.Gson;
import estate.common.FeeRuleUnit;
import estate.common.ParkLotOwnerRole;
import estate.common.util.Convert;
import estate.common.util.GsonUtil;
import estate.common.util.LogUtil;
import estate.dao.*;
import estate.entity.database.*;
import estate.entity.display.ParkLotFeeInfo;
import estate.entity.json.ParkLotExtra;
import estate.entity.json.Select2;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.PropertyNotBindFeeItemException;
import estate.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("billService")
public class BillServiceImpl implements BillService
{
    @Autowired
    private BillDao billDao;
    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private AppUserDao appUserDao;
    @Autowired
    private FeeItemOrderDao feeItemOrderDao;
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private ParkLotOwnerInfoDao parkLotOwnerInfoDao;
    @Autowired
    private FeeItemDao feeItemDao;

    @Override
    public TableData getList(TableFilter tableFilter)
    {
        TableData tableData=billDao.getList(tableFilter);
        tableData.setRecordsTotal(baseDao.count("BillEntity"));
        return tableData;
    }

    @Override
    public ArrayList<BillEntity> getBillByPropertyID(Integer id)
    {
        return billDao.getByPropertyID(id,null);
    }

    @Override
    public ArrayList<BillEntity> getBillByAppUserPhone(String phone,Byte status)
    {
        ArrayList<BillEntity> billEntities=new ArrayList<>();

        //通过电话获取用户类型
        AppUserEntity appUserEntity=appUserDao.getByPhone(phone);
        if (appUserEntity==null)
        {
            return null;
        }

        //先获取该用户所有的物业
        ArrayList<PropertyEntity> propertyEntities;
//        propertyEntities = propertyDao.getPropertiesByPhoneRole(phone,appUserEntity.getUserRole());
//        if (propertyEntities==null)
//            return null;

        //遍历该用户绑定的物业,获取每个物业的账单
//        for (PropertyEntity propertyEntity:propertyEntities)
//        {
//            ArrayList<BillEntity> billEntityArrayList=billDao.getByPropertyID(propertyEntity.getId(),status);
//            billEntities.addAll(billEntityArrayList.stream().collect(Collectors.toList()));
//        }
        return billEntities;
    }

    @Override
    public void generateBillByPropertyID(Integer id) throws PropertyNotBindFeeItemException
    {
        ArrayList<FeeItemOrderEntity> feeItemOrderEntities =feeItemOrderDao.getFeeItemOrdersByPropertyID(id);
        PropertyEntity propertyEntity= (PropertyEntity) baseDao.get(id,PropertyEntity.class);

        if (feeItemOrderEntities==null)
        {
            throw new PropertyNotBindFeeItemException("该物业未绑定任何费用!");
        }

        StringBuilder billInfo=new StringBuilder("");
        BillEntity billEntity=new BillEntity();

        //判断是否已经生成过账单,若是则更新
        ArrayList<BillEntity> billEntities=billDao.getByPropertyID(id, new Byte("0"));
        if (billEntities.size()>0)
        {
            for (BillEntity billEntityTemp:billEntities)
            {
                String lastGenerateTime= Convert.num2time(billEntityTemp.getBillGenerationTime(), "yyyy-MM");
                String now=Convert.num2time(System.currentTimeMillis(),"yyyy-MM");
                if (now.equals(lastGenerateTime))
                {
                    billEntity=billEntityTemp;
                }
            }
        }

        int temp=0;
        for (FeeItemOrderEntity feeItemOrderEntity:feeItemOrderEntities)
        {
            //TODO 如果是服务费的话,生成账单后需要删除该订购
            String kv;
            if (feeItemOrderEntity.getFeeItemEntity().getRuleEntity().getUnit().equals(FeeRuleUnit.SQURE))
            {
                Double unitPrice=new Double(feeItemOrderEntity.getFeeItemEntity().getRuleEntity()
                        .getUnitPrice());
                Double aDouble=unitPrice*propertyEntity.getPropertySquare().doubleValue();
                BigDecimal sum=new BigDecimal(aDouble);
                sum=sum.setScale(2,BigDecimal.ROUND_HALF_UP);
                kv=feeItemOrderEntity.getFeeItemEntity().getName().split(";")[0]+":"+sum;
            }
            else
            {
                kv=feeItemOrderEntity.getFeeItemEntity().getName()+":"+feeItemOrderEntity
                        .getFeeItemEntity().getRuleEntity().getUnitPrice();
            }
            if (temp==0)
            {
                billInfo.append(kv);
            }
            else
            {
                kv=";"+kv;
                billInfo.append(kv);
            }

            temp++;
        }
        billEntity.setFeeItemFee(billInfo.toString());
        billEntity.setPayStatus(new Byte("0"));
        billEntity.setPropertyId(id);
        billEntity.setBillGenerationTime(System.currentTimeMillis());

        Gson gson=new Gson();
        LogUtil.E(gson.toJson(billEntity));

        baseDao.save(billEntity);
    }

    @Override
    public Object getParkLotBillByPhone(String phone)
    {
        ArrayList<ParklotOwnerInfoEntity> parklotOwnerInfoEntities= parkLotOwnerInfoDao.getByPhone(phone);
        ArrayList<Select2> select2s=new ArrayList<>();
        if (parklotOwnerInfoEntities==null)
            return null;
        for (ParklotOwnerInfoEntity parklotOwnerInfoEntity:parklotOwnerInfoEntities)
        {
            FeeItemEntity feeItemEntity= (FeeItemEntity) feeItemDao.getParkLotByVillageIdType(parklotOwnerInfoEntity
                    .getParkingLotEntity()
                    .getVillageId(), String.valueOf(parklotOwnerInfoEntity.getParkingLotEntity().getType()));
            if (feeItemEntity!=null)
            {
                Select2 select2=new Select2();
                ParkLotFeeInfo parkLotFeeInfo = new ParkLotFeeInfo();
                parkLotFeeInfo.setParkLotExtra(GsonUtil.getGson().fromJson(feeItemEntity.getDecription(),
                        ParkLotExtra.class));
                parkLotFeeInfo.setName(feeItemEntity.getName());
                parkLotFeeInfo.setRuleEntity(feeItemEntity.getRuleEntity());
                parkLotFeeInfo.setVillageId(feeItemEntity.getVillageId());
                parkLotFeeInfo.setFeeTypeId(feeItemEntity.getFeeTypeId());
                parkLotFeeInfo.setId(feeItemEntity.getId());
                parkLotFeeInfo.setIsPeriodic(feeItemEntity.getIsPeriodic());
                if (Objects.equals(parklotOwnerInfoEntity.getOwnerType(), ParkLotOwnerRole.OWNER))
                {
                    select2.setId("车位费("+parklotOwnerInfoEntity.getParkingLotEntity().getCode() + ")");
                    select2.setText(parkLotFeeInfo.getRuleEntity().getUnitPrice());
                }
                else if (Objects.equals(parklotOwnerInfoEntity.getOwnerType(), ParkLotOwnerRole.TENANT))
                {
                    select2.setId("车位费("+parklotOwnerInfoEntity.getParkingLotEntity().getCode()+")");
                    select2.setText(parkLotFeeInfo.getParkLotExtra().getMonthPrice());
                }
                else if (Objects.equals(parklotOwnerInfoEntity.getOwnerType(), ParkLotOwnerRole.TEMP))
                {
                    select2.setId("车位费("+parklotOwnerInfoEntity.getParkingLotEntity().getCode()+")");
                    select2.setText(parkLotFeeInfo.getParkLotExtra().getPerTimePrice());
                }
                LogUtil.E(GsonUtil.getGson().toJson(parkLotFeeInfo));
                select2s.add(select2);
            }
        }
        if (select2s.size()>0)
            return select2s;
        return null;
    }
}
