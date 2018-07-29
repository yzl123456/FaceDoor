package estate.controller;

import estate.entity.database.BrakeEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.BaseService;
import estate.service.BrakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 应泽林 on 18-1-14.
 * 道闸控制器
 */
@RestController
@RequestMapping("/web/brake")
public class BrakeController
{
    @Autowired
    private BaseService baseService;
    @Autowired
    private BrakeService brakeService;

    /**
     * 获取道闸列表
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
           return brakeService.getList(tableFilter);
        }
        catch (Exception e)
        {
            return null;
        }
    }


    /**
     * 增加一个道闸
     * @param brakeEntity
     * @param request
     * @return
     */
    @RequestMapping(value = "/add")
    public BasicJson add(BrakeEntity brakeEntity,HttpServletRequest request)
    {
        BasicJson basicJson =new BasicJson();
        try
        {
            baseService.save(brakeEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("添加道闸信息失败\n详细信息:"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 删除一个道闸
     * @param brakeID
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete/{brakeID}")
    public BasicJson delete(@PathVariable Integer brakeID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();

        basicJson.setStatus(true);
        return basicJson;
    }


    /**
     * 通过园区id返回所有的道闸信息
     * @param villageID
     * @param request
     * @return
     */
    @RequestMapping(value = "/getByVillageID/{villageID}")
    public BasicJson getByVillageID(@PathVariable Integer villageID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            basicJson.setJsonString(brakeService.getSelectListByVillageID(villageID));
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取道闸信息失败\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }


}
