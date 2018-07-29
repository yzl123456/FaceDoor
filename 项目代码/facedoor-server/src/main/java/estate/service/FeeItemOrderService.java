package estate.service;

import estate.entity.database.FeeItemOrderEntity;

import java.util.ArrayList;
/**
 * Created by 应泽林 on 18-1-22.
 */
public interface FeeItemOrderService
{
    /**
     * 通过物业id获取该物业绑定的所有的费用项目列表
     * @param id
     * @return
     */
    ArrayList<FeeItemOrderEntity> getFeeItemsByPropertyID(Integer id);
}
