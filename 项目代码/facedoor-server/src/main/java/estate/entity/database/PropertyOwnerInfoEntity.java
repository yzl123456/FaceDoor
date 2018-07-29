package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class PropertyOwnerInfoEntity
{
    private Integer id;
    private Integer propertyId;
    private String phone;
    private Integer buildingId;
    private Byte status;
    private Byte userRole;
    private PropertyEntity propertyEntity;

    public Integer getBuildingId()
    {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId)
    {
        this.buildingId = buildingId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Integer getPropertyId()
    {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId)
    {
        this.propertyId = propertyId;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }

    public Byte getUserRole()
    {
        return userRole;
    }

    public void setUserRole(Byte userRole)
    {
        this.userRole = userRole;
    }

    public PropertyEntity getPropertyEntity()
    {
        return propertyEntity;
    }

    public void setPropertyEntity(PropertyEntity propertyEntity)
    {
        this.propertyEntity = propertyEntity;
    }

}
