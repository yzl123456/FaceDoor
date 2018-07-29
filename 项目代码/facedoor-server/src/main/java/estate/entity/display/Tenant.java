package estate.entity.display;

/**
 * Created by 应泽林 on 18-2-16.
 * 租户数据
 */
public class Tenant
{
    private int tenantId;
    private String phone;
    private String name;
    private String sex;
    private String birthday;
    private String urgentName;
    private String urgentPhone;
    private String identityType;
    private String identityCode;
    private String startTime;
    private String endTime;
    private String authenticationTime;
    private String authStatus;

    private Property property;

    public String getAuthenticationTime()
    {
        return authenticationTime;
    }

    public void setAuthenticationTime(String authenticationTime)
    {
        this.authenticationTime = authenticationTime;
    }

    public String getAuthStatus()
    {
        return authStatus;
    }

    public void setAuthStatus(String authStatus)
    {
        this.authStatus = authStatus;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
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

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Property getProperty()
    {
        return property;
    }

    public void setProperty(Property property)
    {
        this.property = property;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public int getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(int tenantId)
    {
        this.tenantId = tenantId;
    }

    public String getUrgentName()
    {
        return urgentName;
    }

    public void setUrgentName(String urgentName)
    {
        this.urgentName = urgentName;
    }

    public String getUrgentPhone()
    {
        return urgentPhone;
    }

    public void setUrgentPhone(String urgentPhone)
    {
        this.urgentPhone = urgentPhone;
    }


}
