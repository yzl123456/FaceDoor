package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class OpenDoorRecordEntity
{
    private int id;
    private String phone;
    private Long openTime;
    private Byte status;
    private String symbol;
    private String description;
    private Byte level;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Long getOpenTime()
    {
        return openTime;
    }

    public void setOpenTime(Long openTime)
    {
        this.openTime = openTime;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Byte getLevel()
    {
        return level;
    }

    public void setLevel(Byte level)
    {
        this.level = level;
    }
}
