package estate.dao;

import estate.entity.database.BuildingEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 *
 */
public interface BuildingDao
{

    /**
     * 获取datatable表格数据
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);

    /**
     * 通过楼栋编码获取楼栋的所有信息
     * @param code
     * @return
     */
    BuildingEntity getByCode(String code);

    /**
     * 通过园区id获取该园区对应的所有楼栋信息
     * @param id
     * @return
     */
    ArrayList<BuildingEntity> getAllBuildingsByVillageId(Integer id);
}
