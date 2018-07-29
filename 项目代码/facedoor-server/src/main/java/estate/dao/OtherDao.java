package estate.dao;

import estate.entity.database.AppUserEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-4.
 * 门禁访问接口
 */
public interface OtherDao
{	
    TableData getOtherList(TableFilter tableFilter);
	
	
	
	
	
	
	
    /**
     * 根据用户id获取用户信息
     * @param phone
     * @return
     */
    AppUserEntity getUserByPhone(String phone);

    /**
     * 通过用户的电话和状态返回用户信息
     * @param phone
     * @param status
     * @return
     */
    AppUserEntity getByPhoneStatus(String phone , Byte status);

    TableData getOwnerList(TableFilter tableFilter);

    TableData getTenantList(TableFilter tableFilter);

    TableData getAuthenticatedUserList(TableFilter tableFilter);

    TableData getAppUserList(TableFilter tableFilter);

    /**
     * 通过用户类型和电话号码获取用户信息
     * @param phone
     * @param type
     * @return 返回对应的用户实体
     */
    Object getUserInfoByPhoneRole(String phone, int type);

    /**
     * 通过用户的电话和类型删除用户
     * @param phone
     * @param type
     */
    void deleteUserByPhone(String phone,byte type);

    /**
     * 通过物业id获取该物业的所有业主
     * @param id
     * @return
     */
    ArrayList<Object> getOwnersByPropertyID(Integer id);

    /**
     * 通过物业id获取该物业绑定的所有app用户
     * @param id
     * @return
     */
    ArrayList<Object> getAppUserByPropertyID(Integer id);

    /**
     * 获取所有的app用户
     * @return
     */
    ArrayList<AppUserEntity> getAllAppUser();

}
