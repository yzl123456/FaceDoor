package estate.service;

import estate.entity.database.NoticeEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-2-4.
 *公告服务接口
 */
public interface NoticeService
{
    /**
     * 根据filterEntity获取对应的公告
     */
    TableData getList(TableFilter filter);

    /**
     * 获取指定数量的最新的公告
     * @param num 公告数
     * @return 返回公告列表
     */
    ArrayList<NoticeEntity> getNewestNotice(Integer num);

}
