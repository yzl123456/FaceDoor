package estate.service.impl;

import estate.common.config.BindStatus;
import estate.common.config.UserType;
import estate.dao.BaseDao;
import estate.dao.PropertyOwnerInfoDao;
import estate.dao.UserDao;
import estate.entity.app.BindPropertyAppUser;
import estate.entity.app.BindUserInfo;
import estate.entity.database.AppUserEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.database.PropertyOwnerInfoEntity;
import estate.service.PropertyOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("propertyOwnerService")
public class PropertyOwnerServiceImpl implements PropertyOwnerService
{

    @Autowired
    private PropertyOwnerInfoDao propertyOwnerInfoDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BaseDao baseDao;

    @Override
    public void deleteOwnerPropertyBind(String phone, Integer propertyID,Byte role)
    {
        propertyOwnerInfoDao.deleteByPhonePropertyID(phone, propertyID,UserType.OWNER);
    }

    @Override
    public String getRoleStringByPhone(String phone)
    {
        ArrayList<PropertyOwnerInfoEntity> propertyOwnerInfoEntities=propertyOwnerInfoDao.getByPhone(phone);
        if (propertyOwnerInfoEntities!=null)
        {
            StringBuilder roleString=new StringBuilder();
            int temp=0;
            for (PropertyOwnerInfoEntity propertyOwnerInfoEntity:propertyOwnerInfoEntities)
            {
                if (temp==0)
                    roleString.append(propertyOwnerInfoEntity.getUserRole());
                else
                    roleString.append(",").append(propertyOwnerInfoEntity.getUserRole());
                temp++;
            }
            return roleString.toString();
        }

        return null;
    }

    @Override
    public ArrayList<PropertyOwnerInfoEntity> getByPhone(String phone)
    {
        return propertyOwnerInfoDao.getByPhone(phone);
    }

    @Override
    public ArrayList<BindPropertyAppUser> getBindInfoByOwnerInfo(String phone, Byte status)
    {
        ArrayList<PropertyOwnerInfoEntity> propertyOwnerInfoEntities = propertyOwnerInfoDao.getByPhone(phone);
        ArrayList<BindPropertyAppUser> bindPropertyAppUsers=new ArrayList<>();

        //若该业主没有物业,则直接返回null
        if (propertyOwnerInfoEntities==null)
            return null;

        //对业主的每个物业循环
        for (PropertyOwnerInfoEntity propertyOwnerInfoEntity:propertyOwnerInfoEntities)
        {
            if (propertyOwnerInfoEntity.getUserRole().equals(UserType.OWNER))
            {
                //获取到每个物业绑定的非业主用户
                ArrayList<PropertyOwnerInfoEntity> propertyOwnerInfoEntities1 = propertyOwnerInfoDao.
                        getBindBypropertyIDStatus(propertyOwnerInfoEntity.getPropertyId(), status);
                if (propertyOwnerInfoEntities1!=null)
                {
                    ArrayList<BindUserInfo> bindUserInfos = new ArrayList<>();
                    for (PropertyOwnerInfoEntity propertyOwnerInfoEntity1 : propertyOwnerInfoEntities1)
                    {
                        BindUserInfo bindUserInfo = new BindUserInfo();
                        bindUserInfo.setStatus(propertyOwnerInfoEntity1.getStatus());
                        bindUserInfo.setRole(propertyOwnerInfoEntity1.getUserRole());
                        bindUserInfo.setAppUserEntity((AppUserEntity) baseDao.get(propertyOwnerInfoEntity1.getPhone(), AppUserEntity
                                .class));
                        bindUserInfo.setBindId(propertyOwnerInfoEntity1.getId());
                        bindUserInfos.add(bindUserInfo);
                    }

                    //若该物业有绑定关系,则添加,否则丢弃
                    if (bindUserInfos.size() > 0)
                    {
                        BindPropertyAppUser bindPropertyAppUser = new BindPropertyAppUser();
                        bindPropertyAppUser.setPropertyEntity(propertyOwnerInfoEntity.getPropertyEntity());
                        bindPropertyAppUser.setBindUserInfos(bindUserInfos);
                        bindPropertyAppUsers.add(bindPropertyAppUser);
                    }
                }
            }
        }
        if (bindPropertyAppUsers.size()>0)
        {
            return bindPropertyAppUsers;
        }

        return null;
    }

    @Override
    public ArrayList<PropertyOwnerInfoEntity> getByPhonePropertyID(String phone, Integer propertyID)
    {
        return propertyOwnerInfoDao.getByPhonePropertyID(phone,propertyID);
    }

    @Override
    public ArrayList<PropertyOwnerInfoEntity> getByPropertyIdRole(Integer propertyID, Byte role)
    {
        return propertyOwnerInfoDao.getByPropertyIdRole(propertyID,role);
    }

    @Override
    public String addOwnerToProperty(OwnerEntity ownerEntity, Integer propertyID)
    {
        if (propertyOwnerInfoDao.getByPhonePropertyID(ownerEntity.getPhone(),propertyID)!=null)
            return "该物业已经和该用户绑定";
        OwnerEntity ownerEntity1= (OwnerEntity) userDao.getUserInfoByPhoneRole(ownerEntity.getPhone(), UserType.OWNER);
        if (ownerEntity1!=null)
        {
            ownerEntity1.setName(ownerEntity.getName());
            ownerEntity1.setSex(ownerEntity.getSex());
            ownerEntity1.setIdentityType(ownerEntity.getIdentityType());
            ownerEntity1.setIdentityCode(ownerEntity.getIdentityCode());
            ownerEntity1.setAuthenticationTime(ownerEntity.getAuthenticationTime());
            baseDao.save(ownerEntity1);
        }
        else
        {
            baseDao.save(ownerEntity);
        }

        PropertyOwnerInfoEntity propertyOwnerInfoEntity=new PropertyOwnerInfoEntity();
        propertyOwnerInfoEntity.setStatus(BindStatus.CHECKED);
        propertyOwnerInfoEntity.setUserRole(UserType.OWNER);
        propertyOwnerInfoEntity.setPropertyId(propertyID);
        propertyOwnerInfoEntity.setPhone(ownerEntity.getPhone());
        baseDao.save(propertyOwnerInfoEntity);
        return "succ";
    }

    @Override
    public Object getOwnerByPropertyIdRole(Integer propertyID, Byte role)
    {
        ArrayList<PropertyOwnerInfoEntity> propertyOwnerInfoEntities=propertyOwnerInfoDao.
                getByPropertyIdRole(propertyID, role);
        if (propertyOwnerInfoEntities==null)
            return null;
        ArrayList<OwnerEntity> ownerEntities=new ArrayList<>();
        for (PropertyOwnerInfoEntity propertyOwnerInfoEntity:propertyOwnerInfoEntities)
        {
            OwnerEntity ownerEntity= (OwnerEntity) userDao.
                    getUserInfoByPhoneRole(propertyOwnerInfoEntity.getPhone(), UserType.OWNER);
            if (ownerEntity!=null)
                ownerEntities.add(ownerEntity);
        }
        if (ownerEntities.size()>0)
            return ownerEntities;
        return null;
    }


}
