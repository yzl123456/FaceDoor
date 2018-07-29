package estate.service;

import estate.entity.app.BindPropertyAppUser;
import estate.entity.database.OwnerEntity;
import estate.entity.database.PropertyOwnerInfoEntity;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface PropertyOwnerService
{
    /**
     * 通过用户的电话和物业ID以及绑定关系删除对应的业主物业绑定关系<br/>
     *
     * @param phone
     * @param propertyID
     */
    void deleteOwnerPropertyBind(String phone,Integer propertyID,Byte role);

    /**
     * 通过用户的电话返回用户的角色
     * @param phone
     * @return
     */
    String getRoleStringByPhone(String phone);

    /**
     * 通过用户电话返回用户物业关系
     * @param phone
     * @return
     */
    ArrayList<PropertyOwnerInfoEntity> getByPhone(String phone);

    /**
     * 根据业主的电话获取该业主名下物业的所有绑定
     * @param phone
     * @param status 审核状态
     * @return
     */
    ArrayList<BindPropertyAppUser> getBindInfoByOwnerInfo(String phone,Byte status);


    /**
     * 通过物业id和用户电话返回绑定关系
     * @param phone
     * @param propertyID
     * @return
     */
    ArrayList<PropertyOwnerInfoEntity> getByPhonePropertyID(String phone ,Integer propertyID);

    /**
     * 通过物业id和用户角色返回绑定关系<br/>
     * role为null则返回该物业的所有绑定
     * @param propertyID
     * @param role
     * @return
     */
    ArrayList<PropertyOwnerInfoEntity> getByPropertyIdRole(Integer propertyID,Byte role);

    /**
     * 给物业增加业主,如果该业主信息则更新,否则创建
     * @param ownerEntity
     * @param propertyID
     * @return 成功则返回succ,其余为错误信息
     */
    String addOwnerToProperty(OwnerEntity ownerEntity,Integer propertyID);

    /**
     * 根据物业id获取相应的绑定用户关系
     * @param propertyID
     * @param role 绑定关系
     * @return 返回用户信息
     */
    Object getOwnerByPropertyIdRole(Integer propertyID,Byte role);


}
