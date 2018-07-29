package estate.entity.database;

import java.math.BigDecimal;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class PropertyEntity
{
    private Integer id;
    private String code;
    private String location;
    private Byte type;
    private BigDecimal propertySquare;
    private Byte ownerType;
    private Integer villageId;
    private Integer buildingId;
    private Byte status;
    private Byte openDoorStatus;
    private Long modifyTime;
    private BuildingEntity buildingEntity;


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

    public Byte getType()
    {
        return type;
    }

    public void setType(Byte type)
    {
        this.type = type;
    }

    public BigDecimal getPropertySquare()
    {
        return propertySquare;
    }

    public void setPropertySquare(BigDecimal propertySquare)
    {
        this.propertySquare = propertySquare;
    }

    public Byte getOwnerType()
    {
        return ownerType;
    }

    public void setOwnerType(Byte ownerType)
    {
        this.ownerType = ownerType;
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

    public Integer getBuildingId()
    {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId)
    {
        this.buildingId = buildingId;
    }

    public Byte getOpenDoorStatus()
    {
        return openDoorStatus;
    }

    public void setOpenDoorStatus(Byte openDoorStatus)
    {
        this.openDoorStatus = openDoorStatus;
    }

    public Long getModifyTime()
    {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime)
    {
        this.modifyTime = modifyTime;
    }

    public BuildingEntity getBuildingEntity()
    {
        return buildingEntity;
    }

    public void setBuildingEntity(BuildingEntity buildingEntity)
    {
        this.buildingEntity = buildingEntity;
    }
}
