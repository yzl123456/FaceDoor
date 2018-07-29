package estate.controller;

import estate.entity.json.BasicJson;
import estate.entity.json.TableFilter;
import estate.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 应泽林 on 18-1-7.
 *报修管理
 */

@RestController
@RequestMapping("/web/service/repair/")
public class ServiceController
{
    @Autowired
    ServiceService service;
    /**
     * 获取报修的列表
     */
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public BasicJson getList(TableFilter tableFilter)
    {

        BasicJson basicJson=new BasicJson(false);

        basicJson.setJsonString(service.getRepairList(tableFilter));
        return basicJson;
    }



}
