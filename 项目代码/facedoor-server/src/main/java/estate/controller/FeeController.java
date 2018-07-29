package estate.controller;

import estate.common.Config;
import estate.common.config.UserType;
import estate.common.util.Convert;
import estate.common.util.GsonUtil;
import estate.common.util.LogUtil;
import estate.entity.database.FeeItemEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.database.PropertyEntity;
import estate.entity.database.RuleEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.ParkLotExtra;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.PropertyNotBindFeeItemException;
import estate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-14.
 * 提供缴费管理的所有费用类型的增删改查
 */

@RestController
@RequestMapping("/web/fee")
public class FeeController
{
    @Autowired
    private FeeService feeService;
    @Autowired
    private BillService billService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/add/{feeType}")
    public BasicJson addFeeItem(@PathVariable String feeType,HttpServletRequest request)
    {
        RuleEntity ruleEntity=new RuleEntity();
        FeeItemEntity feeItemEntity=new FeeItemEntity();
        BasicJson basicJson=new BasicJson(false);

        try
        {
            ruleEntity.setStartTime(Convert.time2num(request.getParameter("start_time")));
            ruleEntity.setEndTime(Convert.time2num(request.getParameter("end_time")));
            ruleEntity.setUnit(request.getParameter("unit_type"));
            ruleEntity.setUnitPrice(request.getParameter("fee_unit_price"));
            feeItemEntity.setVillageId(Integer.valueOf(request.getParameter("villageId")));
            switch (feeType)
            {
                case "estate":
                    ruleEntity.setOverdueUnit(request.getParameter("overdue_unit_type"));
                    ruleEntity.setOverdueUnitPrice(request.getParameter("overdue_unit_price"));
                    feeItemEntity.setRuleEntity(ruleEntity);
                    String payStartTime=String.valueOf(Convert.time2num(request.getParameter("pay_start_time")));
                    String payEndTime=String.valueOf(Convert.time2num(request.getParameter("pay_end_time")));
                    feeItemEntity.setName(request.getParameter("fee_name") + ";" + payStartTime + ";" + payEndTime);
                    feeItemEntity.setFeeTypeId(Config.ESTATE);
                    break;
                case "service":
                    feeItemEntity.setName(request.getParameter("fee_name"));
                    feeItemEntity.setDecription(request.getParameter("description"));
                    feeItemEntity.setRuleEntity(ruleEntity);

                    feeItemEntity.setFeeTypeId(Config.SERVICE);
                    feeItemEntity.setIsPeriodic(Config.FALSE);
                    break;
                case "parkingLot":
                    break;
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("参数有误!");
            return basicJson;
        }

        try
        {
            feeService.estateFeeAdd(feeItemEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("费用信息增加失败,请重试");
            return basicJson;
        }

        basicJson.setStatus(true);
        basicJson.setJsonString(feeItemEntity);
        return basicJson;
    }


    @RequestMapping(value = "/add/parkLot")
    public BasicJson addParkLotFee(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        FeeItemEntity feeItemEntity=new FeeItemEntity();
        RuleEntity ruleEntity=new RuleEntity();
        ParkLotExtra parkLotExtra=new ParkLotExtra();

        try
        {
            parkLotExtra.setMonthPrice(request.getParameter("monthPrice"));
            parkLotExtra.setPerTimePrice(request.getParameter("perTimePrice"));
            parkLotExtra.setPayEndTime(Convert.time2num(request.getParameter("payEndTime")));
            parkLotExtra.setPayStartTime(Convert.time2num(request.getParameter("payStartTime")));

            ruleEntity.setStartTime(Convert.time2num(request.getParameter("startTime")));
            ruleEntity.setEndTime(Convert.time2num(request.getParameter("endTime")));
            ruleEntity.setOverdueUnitPrice(request.getParameter("overdueUnitPrice"));
            ruleEntity.setOverdueUnit(request.getParameter("overdueUnit"));
            ruleEntity.setUnitPrice(request.getParameter("unitPrice"));
            feeItemEntity.setRuleEntity(ruleEntity);
            feeItemEntity.setName(request.getParameter("parkLotType"));
            feeItemEntity.setDecription(GsonUtil.getGson().toJson(parkLotExtra));
            feeItemEntity.setVillageId(Integer.valueOf(request.getParameter("villageId")));
            feeItemEntity.setFeeTypeId(Config.PARKING_LOT);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("参数错误\n"+e.getMessage());
            return basicJson;
        }

        if (feeService.getParkLotFeeByVillageIdType(feeItemEntity.getVillageId(),feeItemEntity.getName())!=null)
        {
            basicJson.getErrorMsg().setDescription("该园区已经配置该类别车位的费用信息");
            return basicJson;
        }
        try
        {
            feeService.estateFeeAdd(feeItemEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("添加车位费出错\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setJsonString(feeItemEntity);
        basicJson.setStatus(true);
        return basicJson;
    }

    @RequestMapping(value = "/list/{feeType}")
    public TableData feeList(@PathVariable String feeType, TableFilter tableFilter,HttpServletRequest request)
    {
        LogUtil.E(FeeController.class.getName());
        if(request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");
        TableData tableData=new TableData(false);
        try
        {
            switch (feeType)
            {
                case "estate":
                    return feeService.feeList(tableFilter,Config.ESTATE);
                case "service":
                    return feeService.feeList(tableFilter,Config.SERVICE);
                case "parkLot":
                    return feeService.feeList(tableFilter,Config.PARKING_LOT);
                default:
                    tableData.getErrorMsg().setCode("1000525");
                    tableData.getErrorMsg().setDescription("请求路径错误");
                    return tableData;
            }

        }
        catch (Exception e)
        {
//            LogUtil.E(e.getMessage(),"FeeController");
            tableData.getErrorMsg().setCode("1000520");
            tableData.getErrorMsg().setDescription("获取费用列表失败,请重试");
            return tableData;
        }
    }

    /**
     * 将物业和费用项目绑定
     * @param request
     * @return
     */
    @RequestMapping(value = "/relateBuilding")
    public BasicJson relateBuilding(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        Integer feeItemID;
        ArrayList<Integer> buildingIDs;
        try
        {
            feeItemID=Integer.valueOf(request.getParameter("feeItemID"));
            buildingIDs=Convert.string2ints(request.getParameter("buildingIDs"),",");
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("参数错误\n"+e.getMessage());
            return basicJson;
        }
        try
        {
            feeService.relateBuilding(buildingIDs,feeItemID);
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
        }
        LogUtil.E(buildingIDs);
        LogUtil.E(feeItemID);
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 删除费用信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete/{feeId}")
    public BasicJson feeDelete(@PathVariable Integer feeId,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        try
        {
            feeService.deleteFee(feeId);
        }
        catch (Exception e)
        {
//            LogUtil.E(e.getMessage(), "FeeController");
            basicJson.getErrorMsg().setDescription("删除出错,请重试");
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }

    @RequestMapping(value = "/getBillList")
    public TableData getBillList(TableFilter tableFilter,HttpServletRequest request)
    {
        tableFilter.setSearchValue(request.getParameter("search[value]"));
        tableFilter.setStatus(Byte.valueOf(request.getParameter("billStatus")));
        tableFilter.setStartTime(Convert.time2num(request.getParameter("billStartTime")));
        tableFilter.setEndTime(Convert.time2num(request.getParameter("billEndTime")));
        if (tableFilter.getStatus()==-1)
            tableFilter.setStatus(null);
        if (tableFilter.getSearchValue().equals(""))
            tableFilter.setSearchValue(null);
        try
        {
            return billService.getList(tableFilter);
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            return null;
        }
    }

    /**
     * 一键生成所有的账单,若有生成失败的账单则只返回失败的账单
     * @param request
     * @return
     */
    @RequestMapping(value = "/getnerateAllBill")
    public BasicJson generateAllBill(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();

        ArrayList<PropertyEntity> failProperties=new ArrayList<>();
        ArrayList<PropertyEntity> propertyEntities = propertyService.getAllPropertyByVillageID(1);
        if (propertyEntities != null)
        {
            for (PropertyEntity propertyEntity : propertyEntities)
            {
                try
                {
                    billService.generateBillByPropertyID(propertyEntity.getId());
                }
                catch (PropertyNotBindFeeItemException e)
                {
                    failProperties.add(propertyEntity);
                }
            }
            basicJson.setJsonString(failProperties);
        }
        basicJson.setStatus(true);
        return basicJson;
    }

    @RequestMapping(value = "/pushBill/{propertyID}")
    public BasicJson pushBillToUser(@PathVariable Integer propertyID, HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();

        try
        {
            ArrayList<Object> ownerEntities=userService.getUserInfoByProperityID(propertyID, UserType.OWNER);
            LogUtil.E(ownerEntities.size());
            for (Object object:ownerEntities)
            {
                OwnerEntity ownerEntity= (OwnerEntity) object;
//                LogUtil.E(Message.send(ownerEntity.getPhone(), "您有一笔待缴账单，账单生成日期"));
            }
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("短信发送失败");
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }
}
