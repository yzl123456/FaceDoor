package estate.service;

import estate.entity.database.ParklotOwnerInfoEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface ParkLotService
{
    /**
     * 返回datatable数据
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);

    ArrayList<ParklotOwnerInfoEntity> getByParkLotID(Integer id);
}
