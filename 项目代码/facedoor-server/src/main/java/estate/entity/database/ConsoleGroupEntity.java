package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class ConsoleGroupEntity
{
    private int cgId;
    private String name;
    private String authorization;

    public int getCgId()
    {
        return cgId;
    }

    public void setCgId(int cgId)
    {
        this.cgId = cgId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAuthorization()
    {
        return authorization;
    }

    public void setAuthorization(String authorization)
    {
        this.authorization = authorization;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ConsoleGroupEntity that = (ConsoleGroupEntity) o;

        if (cgId != that.cgId)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (authorization != null ? !authorization.equals(that.authorization) : that.authorization != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = cgId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (authorization != null ? authorization.hashCode() : 0);
        return result;
    }
}
