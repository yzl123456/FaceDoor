package estate.controller;

import estate.common.config.RepairStatus;
import estate.entity.database.ConsoleUserEntity;
import estate.entity.database.RepairEntity;
import estate.entity.database.RepairManEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.BaseService;
import estate.service.PictureService;
import estate.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 应泽林 on 18-1-15.
 *
 */
@RestController
@RequestMapping("/web/repair")
public class RepairController
{
    @Autowired
    private RepairService repairService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/list")
    public TableData getList(TableFilter tableFilter,HttpServletRequest request)
    {
        if (request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");

        return repairService.getList(tableFilter);
    }

    /**
     * 删除报修
     * @param repairID
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete/{repairID}")
    public BasicJson deleteRepair(@PathVariable Integer repairID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        if (repairID==null)
        {
            basicJson.getErrorMsg().setDescription("参数错误");
            return basicJson;
        }
        RepairEntity repairEntity=new RepairEntity();
        try
        {
            repairEntity.setId(repairID);
            baseService.delete(repairEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("删除失败");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 根据维修id获取该维修所有的图片路径
     * @param repairID
     * @param request
     * @return
     */
    @RequestMapping(value = "/getPathsByID/{repairID}")
    public BasicJson getPathByID(@PathVariable Integer repairID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            RepairEntity repairEntity = (RepairEntity) baseService.get(repairID, RepairEntity.class);
            basicJson.setJsonString(pictureService.getPathsByIDs(repairEntity.getImageIdList(),request));
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取图片列表失败");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 改变维修的状态
     * @param request
     * @return
     */
    @RequestMapping(value = "/finish/{repairID}")
    public BasicJson changeStatus(@PathVariable Integer repairID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            RepairEntity repairEntity= (RepairEntity) baseService.get(repairID, RepairEntity.class);
            repairEntity.setStatus(RepairStatus.FORCOMMENT);
            repairEntity.setFinishTime(System.currentTimeMillis());
            baseService.save(repairEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode(e.getMessage());
            basicJson.getErrorMsg().setDescription("设置失败");
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 添加维修人员
     * @param repairManEntity
     * @param request
     * @return
     */
    @RequestMapping(value = "/addRepairMan")
    public BasicJson addRepairMan(RepairManEntity repairManEntity,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            baseService.save(repairManEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode(e.getMessage());
            basicJson.getErrorMsg().setDescription("添加失败");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 获取所有的维修人员列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRepairManList")
    public BasicJson getRepairManList(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            basicJson.setJsonString(baseService.getAll(RepairManEntity.class));
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode(e.getMessage());
            basicJson.getErrorMsg().setDescription("获取维修人员信息出错");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 设置维修人员
     * @param request
     * @return
     */
    @RequestMapping(value = "/setRepairMan")
    public BasicJson setRepairMan(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        HttpSession httpSession=request.getSession();
        ConsoleUserEntity consoleUserEntity= (ConsoleUserEntity) httpSession.getAttribute("user");
        try
        {
            Integer repairID=Integer.valueOf(request.getParameter("repairID"));
            Integer repairManId= Integer.valueOf(request.getParameter("repairManID"));
            RepairEntity repairEntity= (RepairEntity) baseService.get(repairID, RepairEntity.class);
            repairEntity.setCuId(consoleUserEntity.getId());
            repairEntity.setStatus(RepairStatus.PROCESSING);
            repairEntity.setRepairManId(repairManId);
            repairEntity.setProcessTime(System.currentTimeMillis());
            baseService.save(repairEntity);

            //TODO 给维修人员发送短信
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode("100015");
            basicJson.getErrorMsg().setDescription("操作失败");
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }
}
