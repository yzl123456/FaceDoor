package estate.controller;

import estate.entity.database.ComplainEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.BaseService;
import estate.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 应泽林 on 18-1-16.
 *
 */
@RestController
@RequestMapping("/web/complain")
public class ComplainController
{
    @Autowired
    private ComplainService complainService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/list")
    public TableData getList(TableFilter tableFilter,HttpServletRequest request)
    {
        if (request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");
        try
        {
            return complainService.getList(tableFilter);
        }
        catch (Exception e)
        {
//            LogUtil.E(e.getMessage());
            return null;
        }
    }

    /**
     * 根据投诉id删除删除该条投诉
     * @param complainID
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete/{complainID}")
    public BasicJson deleteComplain(@PathVariable Integer complainID,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();

        if (complainID==null)
        {
            basicJson.getErrorMsg().setDescription("参数错误!");
            return basicJson;
        }

        ComplainEntity complainEntity=new ComplainEntity();
        complainEntity.setId(complainID);
        try
        {
            baseService.delete(complainEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("删除失败");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }
}
