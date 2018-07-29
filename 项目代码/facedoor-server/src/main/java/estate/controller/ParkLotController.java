package estate.controller;

import estate.common.Config;
import estate.common.config.ParkLot;
import estate.common.util.LogUtil;
import estate.entity.database.ParkingLotEntity;
import estate.entity.database.ParklotOwnerInfoEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.BaseService;
import estate.service.ParkLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 应泽林 on 18-1-14.
 * 车位控制器
 */
@RestController
@RequestMapping("/web/parkLot")
public class ParkLotController
{
    @Autowired
    private BaseService baseService;
    @Autowired
    private ParkLotService parkLotService;

    /**
     * 获取datatable数据
     * @param tableFilter
     * @param request
     * @return
     */
    @RequestMapping(value = "/getList")
    public TableData getList(TableFilter tableFilter,HttpServletRequest request)
    {
        tableFilter.setSearchValue(request.getParameter("search[value]"));
        if (tableFilter.getSearchValue().equals(""))
            tableFilter.setSearchValue(null);

        try
        {
            return parkLotService.getList(tableFilter);
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            return null;
        }
    }


    /**
     * 增加车位信息
     * @param parkingLotEntity
     * @param request
     * @return
     */
    @RequestMapping(value = "/add")
    public BasicJson add(ParkingLotEntity parkingLotEntity,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        parkingLotEntity.setStatus(ParkLot.EMPTY);
        try
        {
            baseService.save(parkingLotEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("保存车位信息失败\n"+e.getMessage());
            return basicJson;
        }
        basicJson.setJsonString(parkingLotEntity);
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 根据车位id删除车位
     * @param parkLotID
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete/{parkLotID}")
    public BasicJson delete(@PathVariable Integer parkLotID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();

        basicJson.setStatus(true);
        return basicJson;
    }


    @RequestMapping(value = "/addOwner")
    public BasicJson addOwner(ParklotOwnerInfoEntity parklotOwnerInfoEntity,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
//        ArrayList<ParklotOwnerInfoEntity> parklotOwnerInfoEntities=parkLotService.getByParkLotID
//                (parklotOwnerInfoEntity.getPlId());
//        if (parklotOwnerInfoEntities!=null)
//        {
//            for (ParklotOwnerInfoEntity parklotOwnerInfoEntity1:parklotOwnerInfoEntities)
//            {
//                if (Objects.equals(parklotOwnerInfoEntity1.getOwnerType(), ParkLotOwnerRole.OWNER)
//                        &&parklotOwnerInfoEntity1.getPlId().equals(parklotOwnerInfoEntity.getPlId()))
//                {
//                    basicJson.getErrorMsg().setDescription("该车位已添加业主");
//                    return basicJson;
//                }
//            }
//        }
        try
        {
            parklotOwnerInfoEntity.setEnterBrakeAllowed(Config.TRUE);
            baseService.save(parklotOwnerInfoEntity);
            basicJson.setJsonString(parklotOwnerInfoEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("添加绑定失败\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    @RequestMapping(value = "/getOwnerList/{parkLotID}")
    public BasicJson getOwnerList(@PathVariable Integer parkLotID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            basicJson.setJsonString(parkLotService.getByParkLotID(parkLotID));
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取绑定用户出错\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 解除绑定
     */
    @RequestMapping(value = "/deleteBind/{id}")
    public BasicJson deleteBind(@PathVariable Integer id,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        ParklotOwnerInfoEntity parklotOwnerInfoEntity=new ParklotOwnerInfoEntity();
        parklotOwnerInfoEntity.setId(id);
        try
        {
            baseService.delete(parklotOwnerInfoEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("解除绑定失败\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }



}
