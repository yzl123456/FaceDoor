package estate.service;

import estate.entity.json.Select2;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface BrakeService
{
    /**
     * 返回datatable显示的数据
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);

    /**
     * 通过园区id返回select2形式的道闸列表
     * @param id
     * @return
     */
    ArrayList<Select2> getSelectListByVillageID(Integer id);
}
