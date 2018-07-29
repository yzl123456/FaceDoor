package estate.dao;

import estate.entity.database.SsidSecretEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

/**
 * Created by 应泽林 on 18-1-21.
 */
public interface SsidSecretDao
{
    /**
     * 通过ssid返回SsidSecretEntity实体
     * @param symbol
     * @return
     */
    SsidSecretEntity getBySymbol(String symbol);

    /**
     * 获取供datatable显示的数据
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);
}
