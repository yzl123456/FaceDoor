package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class ShopEntity
{
    private Integer id;
    private Integer propertyId;
    private String code;
    private Integer villageId;

    public Integer getPropertyId()
    {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId)
    {
        this.propertyId = propertyId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Integer getVillageId()
    {
        return villageId;
    }

    public void setVillageId(Integer villageId)
    {
        this.villageId = villageId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ShopEntity that = (ShopEntity) o;

        if (propertyId != null ? !propertyId.equals(that.propertyId) : that.propertyId != null)
            return false;
        if (code != null ? !code.equals(that.code) : that.code != null)
            return false;
        if (villageId != null ? !villageId.equals(that.villageId) : that.villageId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = propertyId != null ? propertyId.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (villageId != null ? villageId.hashCode() : 0);
        return result;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
