package estate.service;

import estate.entity.database.FeeItemEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-15.
 * 费用接口,提供物业费,服务费和车位费的录入和查询功能
 */
public interface FeeService
{

    /**
     * 增加费用项目
     * @param feeItemEntity
     */
    void estateFeeAdd(FeeItemEntity feeItemEntity);

    /**
     * 根据datatable过滤条件返回datatable格式的项目列表
     * @param tableFilter
     * @return
     */
    TableData feeList(TableFilter tableFilter,int feeType);

    /**
     * 会同时删除与之相关的规则
     * @param id
     */
    void deleteFee(Integer id);

    //TODO 去除删除费用信息的隐患
    /**
     * 将费用和楼栋下的所有物业绑定在一起,执行操作钱会先删除数据库中所有该费用绑定的物业,有一定的隐患
     * @param buildingIDs
     * @param feeItemID
     */
    void relateBuilding(ArrayList<Integer> buildingIDs,Integer feeItemID);

    /**
     * 通过园区id和车位类型获取费用信息,用来防止对一个园区添加多个相同类型的车位费用信息
     * @param villageID
     * @param type
     * @return
     */
    Object getParkLotFeeByVillageIdType(Integer villageID,String type);

}
