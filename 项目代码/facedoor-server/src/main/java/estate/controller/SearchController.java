package estate.controller;

import estate.entity.json.BasicJson;
import estate.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 应泽林 on 18-1-25.
 * 全局的搜索控制器
 */
@RestController
@RequestMapping("/web/search")
public class SearchController
{
    @Autowired
    private SearchService searchService;
    /**
     * 按名称搜索园区
     * @param request
     * @return
     */
    @RequestMapping(value = "/village")
    public BasicJson villageSearch(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        String q=request.getParameter("q");
        if (q==null)
        {
            return basicJson;
        }
        basicJson.setJsonString(searchService.villageByName(q));
        return basicJson;
    }

    /**
     * 根据业主电话返回业主的姓名和电话
     * @param request
     * @return
     */
    @RequestMapping(value = "/owner")
    public BasicJson owerSearch(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        String q=request.getParameter("q");
        if (q==null)
        {
            return basicJson;
        }
//        try
//        {
//        }
        basicJson.setStatus(true);
        basicJson.setJsonString(searchService.ownerSearch(q));
        return basicJson;
    }
}
