package estate.entity.json;

/**
 * Created by 应泽林 on 18-2-16.
 * 存储车位费的额外配置
 */
public class ParkLotExtra
{
    private Long payStartTime;
    private Long payEndTime;
    private String monthPrice;
    private String perTimePrice;

    public String getMonthPrice()
    {
        return monthPrice;
    }

    public void setMonthPrice(String monthPrice)
    {
        this.monthPrice = monthPrice;
    }

    public Long getPayEndTime()
    {
        return payEndTime;
    }

    public void setPayEndTime(Long payEndTime)
    {
        this.payEndTime = payEndTime;
    }

    public Long getPayStartTime()
    {
        return payStartTime;
    }

    public void setPayStartTime(Long payStartTime)
    {
        this.payStartTime = payStartTime;
    }

    public String getPerTimePrice()
    {
        return perTimePrice;
    }

    public void setPerTimePrice(String perTimePrice)
    {
        this.perTimePrice = perTimePrice;
    }
}
