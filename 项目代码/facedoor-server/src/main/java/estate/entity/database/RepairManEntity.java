package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class RepairManEntity
{
    private int id;
    private String phone;
    private String name;


    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
