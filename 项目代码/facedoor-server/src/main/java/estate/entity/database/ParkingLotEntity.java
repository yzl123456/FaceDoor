package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class ParkingLotEntity
{
    private Integer id;
    private String code;
    private String floor;
    private String location;
    private Byte type;
    private String description;
    private Integer brakeId;
    private Integer villageId;
    private Byte status;


    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getFloor()
    {
        return floor;
    }

    public void setFloor(String floor)
    {
        this.floor = floor;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Byte getType()
    {
        return type;
    }

    public void setType(Byte type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getBrakeId()
    {
        return brakeId;
    }

    public void setBrakeId(Integer brakeId)
    {
        this.brakeId = brakeId;
    }

    public Integer getVillageId()
    {
        return villageId;
    }

    public void setVillageId(Integer villageId)
    {
        this.villageId = villageId;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
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
