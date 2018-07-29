package estate.dao;

import estate.entity.database.BrakeEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-15.
 * 道闸数据库访问层
 */
public interface BrakeDao
{
    /**
     * 获取datatable表格数据
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);

    /**
     * 通过园区id获取道闸
     * @param id
     * @return
     */
    ArrayList<BrakeEntity> getByVillageID(Integer id);


}
