package estate.controller;

import estate.common.util.Convert;
import estate.entity.database.AppUserEntity;
import estate.entity.database.ConsoleUserEntity;
import estate.entity.database.NoticeEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.BaseService;
import estate.service.NoticeService;
import estate.service.PictureService;
import estate.service.UserService;
import estate.thirdApi.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-3.
 *
 */
@RestController
@RequestMapping("/web/notice")
public class NoticeController
{
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private UserService userService;


    /**
     * 增加一条公告信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public BasicJson add(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        NoticeEntity noticeEntity=new NoticeEntity();

        HttpSession httpSession=request.getSession();
        ConsoleUserEntity consoleUserEntity= (ConsoleUserEntity) httpSession.getAttribute("user");

        noticeEntity.setTime(System.currentTimeMillis());
        noticeEntity.setContent(request.getParameter("content"));
        noticeEntity.setDescription(request.getParameter("description"));
        noticeEntity.setTitle(request.getParameter("title"));
        noticeEntity.setExpiretime(Convert.time2num(request.getParameter("expiretime")));
        noticeEntity.setCuId(consoleUserEntity.getId());
        try
        {
            baseService.save(noticeEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setCode("100103");
            basicJson.getErrorMsg().setDescription("公告增加失败,请检查您的输入\n"+e.getMessage());
            return basicJson;
        }
        basicJson.setStatus(true);
        basicJson.setJsonString(noticeEntity);
        return basicJson;
    }

    /**
     * 根据公告id获取公告的详细信息
     * @param noticeID
     * @return
     */
    @RequestMapping(value = "/get/{noticeID}")
    public BasicJson get(@PathVariable Integer noticeID)
    {
        BasicJson basicJson=new BasicJson(false);
        NoticeEntity noticeEntity= (NoticeEntity) baseService.get(noticeID,NoticeEntity.class);
        if (noticeEntity==null)
        {
            basicJson.getErrorMsg().setCode("1000010");
            basicJson.getErrorMsg().setDescription("该条公告不存在");
            return basicJson;
        }
        basicJson.setStatus(true);
        basicJson.setJsonString(noticeEntity);
        return basicJson;
    }

    /**
     * 根据公告id删除指定的公告
     * @param noticeID
     * @return
     */
    @RequestMapping(value = "/delete/{noticeID}")
    public BasicJson delete(@PathVariable Integer noticeID)
    {
        BasicJson basicJson=new BasicJson(false);
        NoticeEntity noticeEntity=new NoticeEntity();
        noticeEntity.setId(noticeID);

        try
        {
            baseService.delete(noticeEntity);
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
     * 根据datatable获取某一页的数据内容
     * @param tableFilter datatable的筛选条件
     * @return
     */
    @RequestMapping(value = "/list")
    public TableData pageList(TableFilter tableFilter,HttpServletRequest request)
    {
        tableFilter.setSearchValue(request.getParameter("search[value]"));
        return noticeService.getList(tableFilter);
    }

    /**
     * 将公告通过短信推送给app用户
     * @param noticeID
     * @param request
     * @return
     */
    @RequestMapping(value = "pushNotice/{noticeID}")
    public BasicJson sendMessage(@PathVariable Integer noticeID, HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        NoticeEntity noticeEntity;
        try
        {
            noticeEntity= (NoticeEntity) baseService.get(noticeID, NoticeEntity.class);
            if (noticeEntity==null)
            {
                basicJson.getErrorMsg().setDescription("获取公告失败");
                return basicJson;
            }

            ArrayList<AppUserEntity> appUserEntities= userService.getAllAppUser();
            if (appUserEntities==null)
            {
                basicJson.getErrorMsg().setDescription("暂无用户");
                return basicJson;
            }
            for (AppUserEntity appUserEntity:appUserEntities)
            {
                Message.send(appUserEntity.getPhone(), "公告推送，系统向您推送了一条新的公告[" + noticeEntity.getTitle() + "]，请您登陆app查看");
            }
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取公告失败");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;

    }
}


















