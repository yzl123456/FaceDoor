package estate.service;

import estate.entity.database.BuildingEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface BuildingService
{
    /**
     * 获取datatable表格数据
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);

    /**
     * 通过楼栋代码获取楼栋id获取,不存在则返回null;
     * @param code
     * @return
     */
    Integer getIDByCode(String code);

    /**
     * 通过园区id返回所有的楼栋
     * @param id
     * @return
     */
    ArrayList<BuildingEntity> getByVillageID(Integer id);
}
