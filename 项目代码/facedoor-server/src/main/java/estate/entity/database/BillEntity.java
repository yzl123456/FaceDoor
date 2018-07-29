package estate.entity.database;

import java.math.BigDecimal;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class BillEntity
{
    private int id;
    private Integer propertyId;
    private String feeItemFee;
    private Byte payStatus;
    private Byte payType;
    private Long payTime;
    private Long billGenerationTime;
    private BigDecimal overdueFee;
    private String payer;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public String getFeeItemFee()
    {
        return feeItemFee;
    }

    public void setFeeItemFee(String feeItemFee)
    {
        this.feeItemFee = feeItemFee;
    }

    public Byte getPayStatus()
    {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus)
    {
        this.payStatus = payStatus;
    }

    public Byte getPayType()
    {
        return payType;
    }

    public void setPayType(Byte payType)
    {
        this.payType = payType;
    }

    public Long getPayTime()
    {
        return payTime;
    }

    public void setPayTime(Long payTime)
    {
        this.payTime = payTime;
    }

    public Long getBillGenerationTime()
    {
        return billGenerationTime;
    }

    public void setBillGenerationTime(Long billGenerationTime)
    {
        this.billGenerationTime = billGenerationTime;
    }

    public BigDecimal getOverdueFee()
    {
        return overdueFee;
    }

    public void setOverdueFee(BigDecimal overdueFee)
    {
        this.overdueFee = overdueFee;
    }

    public String getPayer()
    {
        return payer;
    }

    public void setPayer(String payer)
    {
        this.payer = payer;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BillEntity that = (BillEntity) o;

        if (id != that.id)
            return false;
        if (propertyId != null ? !propertyId.equals(that.propertyId) : that.propertyId != null)
            return false;
        if (feeItemFee != null ? !feeItemFee.equals(that.feeItemFee) : that.feeItemFee != null)
            return false;
        if (payStatus != null ? !payStatus.equals(that.payStatus) : that.payStatus != null)
            return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null)
            return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null)
            return false;
        if (billGenerationTime != null ? !billGenerationTime.equals(that.billGenerationTime) : that
                .billGenerationTime != null)
            return false;
        if (overdueFee != null ? !overdueFee.equals(that.overdueFee) : that.overdueFee != null)
            return false;
        if (payer != null ? !payer.equals(that.payer) : that.payer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (propertyId != null ? propertyId.hashCode() : 0);
        result = 31 * result + (feeItemFee != null ? feeItemFee.hashCode() : 0);
        result = 31 * result + (payStatus != null ? payStatus.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (billGenerationTime != null ? billGenerationTime.hashCode() : 0);
        result = 31 * result + (overdueFee != null ? overdueFee.hashCode() : 0);
        result = 31 * result + (payer != null ? payer.hashCode() : 0);
        return result;
    }
}
