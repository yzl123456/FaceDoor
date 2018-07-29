package estate.entity.app;

import estate.entity.database.PropertyEntity;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class BindPropertyAppUser
{
    private PropertyEntity propertyEntity;
    private ArrayList<BindUserInfo> bindUserInfos=new ArrayList<>();

    public PropertyEntity getPropertyEntity()
    {
        return propertyEntity;
    }

    public void setPropertyEntity(PropertyEntity propertyEntity)
    {
        this.propertyEntity = propertyEntity;
    }

    public ArrayList<BindUserInfo> getBindUserInfos()
    {
        return bindUserInfos;
    }

    public void setBindUserInfos(ArrayList<BindUserInfo> bindUserInfos)
    {
        this.bindUserInfos = bindUserInfos;
    }
}
