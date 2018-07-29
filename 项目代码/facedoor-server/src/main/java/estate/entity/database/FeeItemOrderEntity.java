package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class FeeItemOrderEntity
{
    private Integer id;
    private Integer propertyId;
    private Integer feeItemId;
    private Byte isBilled;
    private FeeItemEntity feeItemEntity;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getPropertyId()
    {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId)
    {
        this.propertyId = propertyId;
    }

    public Integer getFeeItemId()
    {
        return feeItemId;
    }

    public void setFeeItemId(Integer feeItemId)
    {
        this.feeItemId = feeItemId;
    }

    public Byte getIsBilled()
    {
        return isBilled;
    }

    public void setIsBilled(Byte isBilled)
    {
        this.isBilled = isBilled;
    }

    public FeeItemEntity getFeeItemEntity()
    {
        return feeItemEntity;
    }

    public void setFeeItemEntity(FeeItemEntity feeItemEntity)
    {
        this.feeItemEntity = feeItemEntity;
    }
}
