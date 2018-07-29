package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class ParklotOwnerInfoEntity
{
    private Integer id;
    private Integer plId;
    private String ownerPhone;
    private Integer brakeId;
    private Byte ownerType;
    private Byte enterBrakeAllowed;
    private ParkingLotEntity parkingLotEntity;


    public Integer getPlId()
    {
        return plId;
    }

    public void setPlId(Integer plId)
    {
        this.plId = plId;
    }

    public String getOwnerPhone()
    {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone)
    {
        this.ownerPhone = ownerPhone;
    }

    public Integer getBrakeId()
    {
        return brakeId;
    }

    public void setBrakeId(Integer brakeId)
    {
        this.brakeId = brakeId;
    }

    public Byte getEnterBrakeAllowed()
    {
        return enterBrakeAllowed;
    }

    public void setEnterBrakeAllowed(Byte enterBrakeAllowed)
    {
        this.enterBrakeAllowed = enterBrakeAllowed;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Byte getOwnerType()
    {
        return ownerType;
    }

    public void setOwnerType(Byte ownerType)
    {
        this.ownerType = ownerType;
    }

    public ParkingLotEntity getParkingLotEntity()
    {
        return parkingLotEntity;
    }

    public void setParkingLotEntity(ParkingLotEntity parkingLotEntity)
    {
        this.parkingLotEntity = parkingLotEntity;
    }
}
