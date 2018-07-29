package estate.dao;

import estate.entity.database.RepairEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-15.
 *
 */
public interface RepairDao
{
    TableData getList(TableFilter tableFilter);

    Integer count();

    void setRepairMan(RepairEntity repairEntity);

    /**
     * 通过电话返回报修信息
     * @param phone
     * @param status
     * @return
     */
    ArrayList<RepairEntity> getByPhone(String phone,Byte status);
}
