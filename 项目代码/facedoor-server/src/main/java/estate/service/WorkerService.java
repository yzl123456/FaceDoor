package estate.service;

import estate.entity.database.AppUserEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.AppUserNotExitException;
import estate.exception.UserTypeErrorException;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-2-4.
 * 工作人员服务
 */
public interface WorkerService
{


    TableData getWorkerList(TableFilter tableFilter);

//    /**
//     * 获取app用户列表,同时返回每个用户绑定的房产id(1,2,3)
//     * @param tableFilter
//     * @return
//     */
//    TableData getAppUserList(TableFilter tableFilter);
//
//    TableData getList(TableFilter tableFilter,Object object);
//
//    /**
//     * 改变app用户的状态
//     * @param appUserEntity
//     */
//    void changeAppUserStatus(AppUserEntity appUserEntity);
//
//    /**
//     * 通过用户的电话和类型获取用户信息
//     * @param phone
//     * @param type
//     * @return
//     */
//    Object getUserInfoByPhoneRole(String phone, int type);
//
//    /**
//     * 通过App用户的电话返回该用户的详细信息
//     * @param phone
//     * @return
//     */
//    void deleteOwner(String phone);
//
//
//
//    /**
//     * 一次性获取所有的app用户
//     * @return
//     */
//    ArrayList<AppUserEntity> getAllAppUser();



}
