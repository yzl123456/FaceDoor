package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class ComplainEntity
{
    private int id;
    private String title;
    private String content;
    private String description;
    private String phone;
    private Long time;
    private String imageIdList;
    private Byte type;
    private Byte status;
    private Integer cuId;
    private Byte remark;
    private String result;
    private ConsoleUserEntity consoleUserEntity;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public Long getTime()
    {
        return time;
    }

    public void setTime(Long time)
    {
        this.time = time;
    }

    public String getImageIdList()
    {
        return imageIdList;
    }

    public void setImageIdList(String imageIdList)
    {
        this.imageIdList = imageIdList;
    }

    public Byte getType()
    {
        return type;
    }

    public void setType(Byte type)
    {
        this.type = type;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }

    public Byte getRemark()
    {
        return remark;
    }

    public void setRemark(Byte remark)
    {
        this.remark = remark;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Integer getCuId()
    {
        return cuId;
    }

    public void setCuId(Integer cuId)
    {
        this.cuId = cuId;
    }

    public ConsoleUserEntity getConsoleUserEntity()
    {
        return consoleUserEntity;
    }

    public void setConsoleUserEntity(ConsoleUserEntity consoleUserEntity)
    {
        this.consoleUserEntity = consoleUserEntity;
    }
}
