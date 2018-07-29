package estate.entity.display;
/**
 * Created by 应泽林 on 18-1-16.
 */
public class Property
{
    private int propertyId;
    private String code;
    private String ownerNameList;
    private String location;
    private String type;
    private String propertySquare;
    private String ownerType;
    private String villageName;
    private String status;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getOwnerNameList()
    {
        return ownerNameList;
    }

    public void setOwnerNameList(String ownerNameList)
    {
        this.ownerNameList = ownerNameList;
    }

    public String getOwnerType()
    {
        return ownerType;
    }

    public void setOwnerType(String ownerType)
    {
        this.ownerType = ownerType;
    }

    public int getPropertyId()
    {
        return propertyId;
    }

    public void setPropertyId(int propertyId)
    {
        this.propertyId = propertyId;
    }

    public String getPropertySquare()
    {
        return propertySquare;
    }

    public void setPropertySquare(String propertySquare)
    {
        this.propertySquare = propertySquare;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getVillageName()
    {
        return villageName;
    }

    public void setVillageName(String villageName)
    {
        this.villageName = villageName;
    }
}
