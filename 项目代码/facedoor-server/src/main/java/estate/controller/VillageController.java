package estate.controller;

import estate.entity.database.VillageEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.BaseService;
import estate.service.BuildingService;
import estate.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 应泽林 on 18-1-15.
 *
 */
@RestController
@RequestMapping("/web/village")
public class VillageController
{
    @Autowired
    private VillageService villageService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BuildingService buildingService;

    @RequestMapping(value = "/getList")
    public TableData getList(TableFilter tableFilter,HttpServletRequest request)
    {
        tableFilter.setSearchValue(request.getParameter("search[value]"));
        if (tableFilter.getSearchValue().equals(""))
            tableFilter.setSearchValue(null);
        try
        {
            return villageService.getList(tableFilter);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 增加园区信息
     * @param villageEntity
     * @param request
     * @return
     */
    @RequestMapping(value = "/add")
    public BasicJson addVillage(VillageEntity villageEntity,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        try
        {
            baseService.save(villageEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode("10294320");
            basicJson.getErrorMsg().setDescription("添加出错,请重试.\n错误详情:"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 删除园区,如果该园区下面有楼栋的话将不能删除
     * @param villageID
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete/{villageID}")
    public BasicJson delete(@PathVariable Integer villageID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        if (buildingService.getByVillageID(villageID)!=null)
        {
            basicJson.getErrorMsg().setDescription("删除失败,请先删除该园区下面的所有楼栋");
            return basicJson;
        }

        VillageEntity villageEntity=new VillageEntity();
        villageEntity.setId(villageID);
        try
        {
            baseService.delete(villageEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("删除失败\n"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }
}
