package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class BrakeEntity
{
    private int id;
    private Integer parkLotNum;
    private String code;
    private String name;
    private String description;
    private Integer villageId;



    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getVillageId()
    {
        return villageId;
    }

    public void setVillageId(Integer villageId)
    {
        this.villageId = villageId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Integer getParkLotNum()
    {
        return parkLotNum;
    }

    public void setParkLotNum(Integer parkLotNum)
    {
        this.parkLotNum = parkLotNum;
    }
}
