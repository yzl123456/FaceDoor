package estate.service.impl;

import estate.dao.BaseDao;
import estate.dao.NoticeDao;
import estate.entity.database.NoticeEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("notice")
public class NoticeServiceImpl implements NoticeService
{
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private BaseDao baseDao;

    public TableData getList(TableFilter filter)
    {
        TableData tableData=noticeDao.getList(filter);
        tableData.setRecordsTotal(baseDao.count("NoticeEntity"));
        return tableData;
    }

    /**
     * 获取指定数量的最新的公告
     *
     * @param num 公告数
     * @return 返回公告列表
     */
    public ArrayList<NoticeEntity> getNewestNotice(Integer num)
    {
        return noticeDao.getSome(num);
    }
}
