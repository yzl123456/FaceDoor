package estate.dao;

import estate.entity.database.FeeItemEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

/**
 * Created by 应泽林 on 18-1-15.
 *
 */
public interface FeeItemDao
{
    Integer save(FeeItemEntity feeItemEntity);

    void delete(Integer feeItemID);

    FeeItemEntity get(Integer feeItemID);

    TableData getList(TableFilter tableFilter,int feeType);

    Integer count(int feeType);

    /**
     * 通过园区id和车位费类型返回车位费信息
     * @param villageID
     * @param type
     * @return
     */
    Object getParkLotByVillageIdType(Integer villageID,String type);

}
