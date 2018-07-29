package estate.service;

import estate.entity.database.RepairEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-2-15.
 * 维修服务
 */
public interface RepairService
{
    TableData getList(TableFilter tableFilter);

    /**
     * 通过用户的电话获取用户的报修信息
     * @param phone
     * @param status
     * @return
     */
    ArrayList<RepairEntity> getRepairByPhone(String phone,Byte status);

}
