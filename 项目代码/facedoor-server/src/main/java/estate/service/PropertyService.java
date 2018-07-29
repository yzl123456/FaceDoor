package estate.service;

import estate.entity.database.PropertyEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.EntityTypeErrorException;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface PropertyService
{

    TableData getList(TableFilter tableFilter);

    /**
     * 获取所有的园区信息,以select2的数组返回
     * @return
     */
    Object getAllVillage ();

    /**
     * 通过园区的id获取该园区下所有的楼栋信息,以select2的数组返回
     * @param id
     * @return
     */
    Object getBuildingsByValliageId(Integer id);

    /**
     * 通过楼栋id获取该楼栋下面的所有物业,以select2的数组返回
     * @param buildingID
     * @return
     */
    Object getPropertyByBuildingId(Integer buildingID);


    /**
     * 通过楼栋id返回该楼栋下的所有物业
     * @param id
     * @return
     */
    ArrayList<PropertyEntity> getByBuildingID(Integer id);

    /**
     * 根据APP用户的电话号码返回该app用户名下所有的物业
     * @param phone
     * @return
     */
    ArrayList<PropertyEntity> getProperitiesByAppUserPhone(String phone);

    /**
     * 通过物业编号获取物业信息
     * @param code
     * @return
     */
    PropertyEntity getByCode(String code) throws EntityTypeErrorException;

    /**
     * 通过用户电话和类型获取用户绑定的所有的物业
     * @param phone
     * @return
     */
    ArrayList<PropertyEntity> getPropertyByPhoneRole(String phone, Byte userRole);

    /**
     * 通过园区id获取该园区下的所有物业
     * @param id
     * @return
     */
    ArrayList<PropertyEntity> getAllPropertyByVillageID(Integer id);


}
