package estate.dao;

import estate.entity.database.VillageEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-26.
 *
 */
public interface VillageDao
{

    /**
     *
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);

    /**
     * select2的格式将所有的园区返回
     * @return
     */
    ArrayList<VillageEntity> getAllVillage();


}
