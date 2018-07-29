package estate.dao;

import estate.entity.database.ParklotOwnerInfoEntity;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-16.
 *
 */
public interface ParkLotOwnerInfoDao
{
    /**
     * 通过车位id返回该车位绑定的用户
     * @param id
     * @return
     */
    ArrayList<ParklotOwnerInfoEntity> getByParkLotID(Integer id);

    /**
     * 通过用户电话返回该用户绑定的所有车位信息
     * @param phone
     * @return
     */
    ArrayList<ParklotOwnerInfoEntity> getByPhone(String phone);
}
