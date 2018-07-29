package estate.app;

import estate.common.util.LogUtil;
import estate.entity.json.BasicJson;
import estate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 应泽林 on 18-3-7.
 *
 */
@RestController
@RequestMapping(value = "/api/query")
public class QueryHandler
{

    @Autowired
    private PropertyService propertyService;

    /**
     * 获取所有的园区
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllVillage",method = RequestMethod.GET)
    public BasicJson getAllVillage(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            basicJson.setJsonString(propertyService.getAllVillage());
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode("1234230");
            basicJson.getErrorMsg().setDescription("获取园区信息失败");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 根据园区id获取属于该园区的所有楼栋
     * @param villageID
     * @param request
     * @return
     */
    @RequestMapping(value = "/getBuilding/{villageID}",method = RequestMethod.GET)
    public BasicJson getBuildingByVillageID(@PathVariable Integer villageID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            basicJson.setJsonString(propertyService.getBuildingsByValliageId(villageID));
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            basicJson.getErrorMsg().setCode("1234230");
            basicJson.getErrorMsg().setDescription("获取楼栋信息失败"+e.getMessage());
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 根据园区id和楼栋id获取属于该楼栋的所有物业
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProperty",method = RequestMethod.GET)
    public BasicJson getPropertyByBuildingID(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        Integer villageID;
        Integer buildingID;
        try
        {
            villageID = Integer.valueOf(request.getParameter("villageID"));
            buildingID = Integer.valueOf(request.getParameter("buildingID"));
            LogUtil.E(buildingID);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("参数不合法!");
            return basicJson;
        }
        try
        {
            basicJson.setJsonString(propertyService.getPropertyByBuildingId(buildingID));
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            basicJson.getErrorMsg().setCode("100000");
            basicJson.getErrorMsg().setDescription("获取物业信息失败");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

}
