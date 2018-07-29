package estate.dao;

import estate.entity.database.PropertyEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-16.
 *
 */
public interface PropertyDao
{
    TableData getList(TableFilter tableFilter);

    /**
     * 根据楼栋的id获取物业
     * @param id
     * @return
     */
    ArrayList<PropertyEntity> getPropertyByBuildingID(Integer id);

    /**
     * 根据用户的电话和类型获取绑定的所有物业
     * @param phone
     * @param role
     * @return
     */
    ArrayList<PropertyEntity> getPropertiesByPhoneRole(String phone,Byte role);


    /**
     * 获取所有的物业
     * @return
     */
    ArrayList<PropertyEntity> getAllProperty();

    /**
     * 通过编号获取物业信息
     * @param code
     * @return
     */
    PropertyEntity getByCode(String code);

}
