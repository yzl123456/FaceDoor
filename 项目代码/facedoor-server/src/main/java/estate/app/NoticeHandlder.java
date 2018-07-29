package estate.app;

import estate.common.HtmlSplit;
import estate.entity.app.Notice;
import estate.entity.database.NoticeEntity;
import estate.entity.json.BasicJson;
import estate.service.BaseService;
import estate.service.NoticeService;
import estate.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-3-5.
 * 提供公告推送的API
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeHandlder
{
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private PictureService pictureService;

    /**
     * 获取指定数量的公告,按时间排序
     * @param number 数量
     * @return
     */
    @RequestMapping(value = "/getSome/{number}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public BasicJson getSome(@PathVariable Integer number,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        try
        {
            ArrayList<NoticeEntity> noticeEntities=noticeService.getNewestNotice(number);
            ArrayList<Notice> notices =null;
            if (noticeEntities!=null)
            {
                notices = new ArrayList<>();
                for (NoticeEntity noticeEntity : noticeEntities)
                {
                    Notice notice = new Notice(
                            null,
                            noticeEntity.getTime(),
                            noticeEntity.getDescription(),
                            noticeEntity.getId(),
                            noticeEntity.getTitle()
                    );

                    notice.setPicturePathList(pictureService.getPathsByIDs(noticeEntity.getPictureIdList(),request));
                    notices.add(notice);
                }
            }

            basicJson.setJsonString(notices);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取公告错误");
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 根据id获取某个公告的详细信息
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
     * 获取该公告详情的webview显示
     * @param noticeID
     * @return
     */
    @RequestMapping(value = "/getContent/{noticeID}",produces = "text/html;charset=UTF-8")
    public String getCOntent(@PathVariable Integer noticeID)
    {
        NoticeEntity noticeEntity= (NoticeEntity) baseService.get(noticeID,NoticeEntity.class);
        if (noticeEntity==null)
        {
            return null;
        }
        return HtmlSplit.header+noticeEntity.getContent()+HtmlSplit.footer;
    }
}
