package estate.service;

import estate.entity.database.AppUserEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.AppUserNotExitException;
import estate.exception.UserTypeErrorException;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-4.
 * 用户服务
 */
public interface UserService
{

    /**
     * app用户注册
     * @param appUserEntity
     * @param propertyID
     */
    void register(AppUserEntity appUserEntity,Integer propertyID);

    TableData getOwnerList(TableFilter tableFilter);

    TableData getTenantList(TableFilter tableFilter);

    TableData getAuthenticatedUserList(TableFilter tableFilter);

    /**
     * 获取app用户列表,同时返回每个用户绑定的房产id(1,2,3)
     * @param tableFilter
     * @return
     */
    TableData getAppUserList(TableFilter tableFilter);

    TableData getList(TableFilter tableFilter,Object object);

    /**
     * 改变app用户的状态
     * @param appUserEntity
     */
    void changeAppUserStatus(AppUserEntity appUserEntity);

    /**
     * 通过用户的电话和类型获取用户信息
     * @param phone
     * @param type
     * @return
     */
    Object getUserInfoByPhoneRole(String phone, int type);

    /**
     * 通过App用户的电话返回该用户的详细信息
     * @param phone
     * @return
     */
    Object getUserDetailByPhone(String phone) throws AppUserNotExitException;

    /**
     * 删除业主
     * @param phone
     */
    void deleteOwner(String phone);

    /**
     * 通过物业id和用户类型返回该物业对应的用户信息
     * @param id
     * @return
     */
    ArrayList<Object> getUserInfoByProperityID(Integer id,int userType) throws UserTypeErrorException;


    /**
     * 一次性获取所有的app用户
     * @return
     */
    ArrayList<AppUserEntity> getAllAppUser();



}
