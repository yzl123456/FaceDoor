package estate.service;

import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface ApartmentService
{
    TableData getList(TableFilter tableFilter);

}
