package estate.entity.display;

import estate.entity.database.AppUserEntity;
import estate.entity.database.ConsoleUserEntity;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class Complain
{
    private int id;
    private String title;
    private String content;
    private String description;
    private String time;
    private String imageList;
    private String type;
    private String status;
    private String remark;
    private String result;
    private ConsoleUserEntity consoleUserEntity;

    private AppUserEntity appUserEntity;

    public AppUserEntity getAppUserEntity()
    {
        return appUserEntity;
    }

    public void setAppUserEntity(AppUserEntity appUserEntity)
    {
        this.appUserEntity = appUserEntity;
    }

    public ConsoleUserEntity getConsoleUserEntity()
    {
        return consoleUserEntity;
    }

    public void setConsoleUserEntity(ConsoleUserEntity consoleUserEntity)
    {
        this.consoleUserEntity = consoleUserEntity;
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }


    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getImageList()
    {
        return imageList;
    }

    public void setImageList(String imageList)
    {
        this.imageList = imageList;
    }
}
