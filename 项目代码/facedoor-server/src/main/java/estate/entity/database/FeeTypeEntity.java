package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class FeeTypeEntity
{
    private int ftId;
    private String name;
    private String description;

    public int getFtId()
    {
        return ftId;
    }

    public void setFtId(int ftId)
    {
        this.ftId = ftId;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FeeTypeEntity that = (FeeTypeEntity) o;

        if (ftId != that.ftId)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = ftId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
