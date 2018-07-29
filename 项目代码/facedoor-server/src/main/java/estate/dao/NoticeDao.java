package estate.dao;

import estate.entity.database.NoticeEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-4.
 * 公告数据访问接口
 */
public interface NoticeDao
{

    ArrayList<NoticeEntity> getSome(Integer num);

    TableData getList(TableFilter tableFilter);
}
