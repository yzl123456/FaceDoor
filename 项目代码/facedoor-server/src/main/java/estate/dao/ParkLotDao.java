package estate.dao;

import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

/**
 * Created by 应泽林 on 18-1-15.
 *
 */
public interface ParkLotDao
{
    TableData getList(TableFilter tableFilter);
}
