package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class ConsoleUserEntity
{
    private int id;
    private String password;
    private String phone;
    private String email;
    private Integer consoleGroupId;
    private String name;
    private Byte identityType;
    private String identityId;
    private Integer remark;



    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getConsoleGroupId()
    {
        return consoleGroupId;
    }

    public void setConsoleGroupId(Integer consoleGroupId)
    {
        this.consoleGroupId = consoleGroupId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Byte getIdentityType()
    {
        return identityType;
    }

    public void setIdentityType(Byte identityType)
    {
        this.identityType = identityType;
    }

    public String getIdentityId()
    {
        return identityId;
    }

    public void setIdentityId(String identityId)
    {
        this.identityId = identityId;
    }

    public Integer getRemark()
    {
        return remark;
    }

    public void setRemark(Integer remark)
    {
        this.remark = remark;
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
