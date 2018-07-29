package estate.entity.display;

import estate.entity.database.AppUserEntity;
import estate.entity.database.PropertyEntity;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class PropertyAppUser
{
    private PropertyEntity propertyEntity;
    private ArrayList<AppUserEntity> appUserEntities;


    public PropertyEntity getPropertyEntity()
    {
        return propertyEntity;
    }

    public void setPropertyEntity(PropertyEntity propertyEntity)
    {
        this.propertyEntity = propertyEntity;
    }

    public ArrayList<AppUserEntity> getAppUserEntities()
    {
        return appUserEntities;
    }

    public void setAppUserEntities(ArrayList<AppUserEntity> appUserEntities)
    {
        this.appUserEntities = appUserEntities;
    }
}
