package estate.entity.display;
/**
 * Created by 应泽林 on 18-1-16.
 */
public class Family
{
    private String phone;
    private String name;
    private String identityType;
    private String identityCode;
    private String ownerRelationship;
    private String birthday;
    private String sex;

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getIdentityCode()
    {
        return identityCode;
    }

    public void setIdentityCode(String identityCode)
    {
        this.identityCode = identityCode;
    }

    public String getIdentityType()
    {
        return identityType;
    }

    public void setIdentityType(String identityType)
    {
        this.identityType = identityType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOwnerRelationship()
    {
        return ownerRelationship;
    }

    public void setOwnerRelationship(String ownerRelationship)
    {
        this.ownerRelationship = ownerRelationship;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }
}
