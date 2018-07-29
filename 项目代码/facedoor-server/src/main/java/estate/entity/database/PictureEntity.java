package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class PictureEntity
{
    private int id;
    private String name;
    private Long uploadTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getUploadTime()
    {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime)
    {
        this.uploadTime = uploadTime;
    }
}
