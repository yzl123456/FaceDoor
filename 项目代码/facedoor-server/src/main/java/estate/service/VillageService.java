package estate.service;

import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface VillageService
{
    /**
     * 获取datatable表格数据
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);
}
