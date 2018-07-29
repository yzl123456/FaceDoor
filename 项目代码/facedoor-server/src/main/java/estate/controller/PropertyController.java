package estate.controller;

import estate.common.config.UserType;
import estate.common.util.LogUtil;
import estate.entity.database.PropertyEntity;
import estate.entity.database.PropertyOwnerInfoEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.EntityTypeErrorException;
import estate.exception.PropertyNotBindFeeItemException;
import estate.exception.UserTypeErrorException;
import estate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-23.
 * 增加物业,增加园区,增加楼栋,查看物业
 */
@RestController
@RequestMapping("/web/property")
public class PropertyController
{
    @Autowired
    private BaseService baseService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyOwnerService propertyOwnerService;
    @Autowired
    protected UserService userService;
    @Autowired
    private FeeItemOrderService feeItemOrderService;
    @Autowired
    private BillService billService;

    /**
     * 增加物业信息
     * @param propertyEntity
     * @param request
     * @return
     */
    @RequestMapping(value = "/add")
    public BasicJson addProperty(PropertyEntity propertyEntity, HttpServletRequest request)
    {
        BasicJson basicJson = new BasicJson(false);
        try
        {
            if (propertyService.getByCode(propertyEntity.getCode())!=null)
            {
                basicJson.getErrorMsg().setDescription("该编号已存在!");
                return basicJson;
            }
        }
        catch (EntityTypeErrorException e)
        {
            basicJson.getErrorMsg().setDescription("内部参数错误,请查看日志文件");
            return basicJson;
        }
        try
        {
            propertyEntity.setModifyTime(System.currentTimeMillis());
            baseService.save(propertyEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode("1023240");
            basicJson.getErrorMsg().setDescription("添加物业信息失败:" + e.getMessage());
            return basicJson;
        }
        basicJson.setStatus(true);
        basicJson.setJsonString(propertyEntity.getId());
        return basicJson;
    }


    /**
     * 修改物业信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/modify")
    public BasicJson modifyProperty(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        PropertyEntity propertyEntity;
        try
        {
            Integer id= Integer.valueOf(request.getParameter("id"));
            propertyEntity= (PropertyEntity) baseService.get(id,PropertyEntity.class);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("未获取到正确的参数信息!\n"+e.getMessage());
            return basicJson;
        }
        try
        {
//            propertyEntity.setLocation(request.getParameter("location"));
//            propertyEntity.setPropertySquare(new BigDecimal(request.getParameter("propertySquare")).setScale(2,
//                    BigDecimal.ROUND_HALF_UP));
            propertyEntity.setStatus(Byte.valueOf(request.getParameter("status")));
            propertyEntity.setModifyTime(System.currentTimeMillis());
//            propertyEntity.setType(Byte.valueOf(request.getParameter("type")));
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("参数解析错误\n"+e.getMessage());
            return basicJson;
        }
        try
        {
            baseService.save(propertyEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("修改出错\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        basicJson.setJsonString(propertyEntity);
        return basicJson;
    }

    /**
     * 直接获取所有园区
     * @param request
     * @return
     */
    @RequestMapping(value = "/villageList")
    public BasicJson villageList(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        try
        {
            basicJson.setJsonString(propertyService.getAllVillage());
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode("1234230");
            basicJson.getErrorMsg().setDescription("获取园区信息失败\n错误详情:"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }


    @RequestMapping(value = "/deleteOwner")
    public BasicJson deleteOwnerByPhone(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        String phone;
        Integer propertyID;
        try
        {
            phone=request.getParameter("phone");
            propertyID= Integer.valueOf(request.getParameter("propertyID"));
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("参数错误!\n"+e.getMessage());
            return basicJson;
        }

        try
        {
            propertyOwnerService.deleteOwnerPropertyBind(phone, propertyID, UserType.OWNER);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("删除失败\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 通过物业id删除物业,如绑定业主,则不能删除
     * @param propertyID
     * @param request
     * @return
     * @throws UserTypeErrorException
     */
    @RequestMapping(value = "/deleteProperty/{propertyID}")
    public BasicJson deleteProperty(@PathVariable Integer propertyID,HttpServletRequest request)

    {
        BasicJson basicJson=new BasicJson();
        ArrayList<PropertyOwnerInfoEntity> ownerInfoEntities=propertyOwnerService.getByPropertyIdRole(propertyID, null);
        if (ownerInfoEntities!=null)
        {
            basicJson.getErrorMsg().setDescription("请先解除该物业的绑定关系后再删除");
            return basicJson;
        }

        PropertyEntity propertyEntity=new PropertyEntity();
        propertyEntity.setId(propertyID);
        try
        {
            baseService.delete(propertyEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("删除失败\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 根据园区的id获取该园区下面所有的楼栋
     * @param villageId
     * @return
     */
    @RequestMapping(value = "/buildingList/{villageId}")
    public BasicJson buildingList(@PathVariable Integer villageId)
    {
        BasicJson basicJson=new BasicJson(false);
        try
        {
            basicJson.setJsonString(propertyService.getBuildingsByValliageId(villageId));
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode("1234230");
            basicJson.getErrorMsg().setDescription("获取园区信息失败\n错误详情:"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }


    /**
     * 获取物业信息列表
     *
     * @param tableFilter
     * @param request
     * @return
     */
    @RequestMapping(value = "/propertyList")
    public TableData getPropertyList(TableFilter tableFilter, HttpServletRequest request)
    {
        tableFilter.setSearchValue(request.getParameter("search[value]"));
        if (tableFilter.getSearchValue().equals(""))
            tableFilter.setSearchValue(null);
        tableFilter.setType(Byte.valueOf(request.getParameter("propertyType")));
        if (tableFilter.getType()==0)
            tableFilter.setType(null);
        tableFilter.setStatus(Byte.valueOf(request.getParameter("propertyStatus")));
        if (tableFilter.getStatus()==0)
            tableFilter.setStatus(null);

        try
        {
            return propertyService.getList(tableFilter);
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            return null;
        }
    }

    /**
     * 根据物业ID获取更多物业信息
     *
     * @param type       费用(fee),业主(owner)
     * @param propertyID 物业id
     * @return
     */
    @RequestMapping(value = "/{type}/{propertyID}")
    public BasicJson getOwnerInfoByPropertyID(@PathVariable String type, @PathVariable Integer propertyID)
    {
        BasicJson basicJson = new BasicJson(false);

        switch (type)
        {
            case "fee":
                basicJson.setJsonString(feeItemOrderService.getFeeItemsByPropertyID(propertyID));
                break;
            case "owner":
                basicJson.setJsonString(propertyOwnerService.getOwnerByPropertyIdRole(propertyID, UserType.OWNER));
                break;
            case "allOwner":
                basicJson.setJsonString(propertyOwnerService.getByPropertyIdRole(propertyID,null));
                break;
            default:
                basicJson.getErrorMsg().setDescription("参数错误!");
                return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }


    @RequestMapping(value = "/getMoreInfo/{propertyID}")
    public BasicJson getMoreInfoByPropertyID(@PathVariable Integer propertyID)
    {
        BasicJson basicJson =new BasicJson();
        ArrayList<Object> objects=new ArrayList<>();
        try
        {
            PropertyEntity propertyEntity= (PropertyEntity) baseService.get(propertyID,PropertyEntity.class);
            objects.add(propertyEntity);
            objects.add(userService.getUserInfoByProperityID(propertyID, UserType.OWNER));
            basicJson.setJsonString(objects);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取物业信息失败");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    @RequestMapping(value = "/generateBill/{propertyID}")
    public BasicJson generateBill(@PathVariable Integer propertyID)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            billService.generateBillByPropertyID(propertyID);
        }
        catch (PropertyNotBindFeeItemException p)
        {
            basicJson.getErrorMsg().setDescription(p.getMessage());
            return basicJson;
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            basicJson.getErrorMsg().setDescription("生成账单失败");
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }

}
