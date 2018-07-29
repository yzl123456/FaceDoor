package estate.dao;

import estate.entity.database.ComplainEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-16.
 * 用户投诉dao
 */
public interface ComplainDao
{
    TableData getList(TableFilter tableFilter);

    /**
     * 通过电话返回投诉信息
     * @param phone
     * @return
     */
    ArrayList<ComplainEntity> getByPhone(String phone,Byte status);
}
