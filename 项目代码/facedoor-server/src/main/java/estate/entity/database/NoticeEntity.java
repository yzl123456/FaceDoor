package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class NoticeEntity
{
    private Integer id;
    private String title;
    private String content;
    private Long time;
    private String pictureIdList;
    private String description;
    private Byte type;
    private Long expiretime;
    private Integer cuId;
    private ConsoleUserEntity consoleUserEntity;


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

    public Long getTime()
    {
        return time;
    }

    public void setTime(Long time)
    {
        this.time = time;
    }

    public String getPictureIdList()
    {
        return pictureIdList;
    }

    public void setPictureIdList(String pictureIdList)
    {
        this.pictureIdList = pictureIdList;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Byte getType()
    {
        return type;
    }

    public void setType(Byte type)
    {
        this.type = type;
    }

    public Long getExpiretime()
    {
        return expiretime;
    }

    public void setExpiretime(Long expiretime)
    {
        this.expiretime = expiretime;
    }

    public Integer getCuId()
    {
        return cuId;
    }

    public void setCuId(Integer cuId)
    {
        this.cuId = cuId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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
