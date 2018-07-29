package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class TenantEntity
{
    private int tenantId;
    private String phone;
    private String name;
    private Byte sex;
    private Long birthday;
    private String urgentName;
    private String urgentPhone;
    private Byte identityType;
    private String identityCode;
    private Long startTime;
    private Long endTime;
    private Long authenticationTime;
    private Byte authStatus;
    private Integer propertyId;

    public int getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(int tenantId)
    {
        this.tenantId = tenantId;
    }

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

    public Byte getSex()
    {
        return sex;
    }

    public void setSex(Byte sex)
    {
        this.sex = sex;
    }

    public Long getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Long birthday)
    {
        this.birthday = birthday;
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

    public Byte getIdentityType()
    {
        return identityType;
    }

    public void setIdentityType(Byte identityType)
    {
        this.identityType = identityType;
    }

    public String getIdentityCode()
    {
        return identityCode;
    }

    public void setIdentityCode(String identityCode)
    {
        this.identityCode = identityCode;
    }

    public Long getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Long startTime)
    {
        this.startTime = startTime;
    }

    public Long getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Long endTime)
    {
        this.endTime = endTime;
    }

    public Long getAuthenticationTime()
    {
        return authenticationTime;
    }

    public void setAuthenticationTime(Long authenticationTime)
    {
        this.authenticationTime = authenticationTime;
    }

    public Byte getAuthStatus()
    {
        return authStatus;
    }

    public void setAuthStatus(Byte authStatus)
    {
        this.authStatus = authStatus;
    }

    public Integer getPropertyId()
    {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId)
    {
        this.propertyId = propertyId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TenantEntity that = (TenantEntity) o;

        if (tenantId != that.tenantId)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null)
            return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null)
            return false;
        if (urgentName != null ? !urgentName.equals(that.urgentName) : that.urgentName != null)
            return false;
        if (urgentPhone != null ? !urgentPhone.equals(that.urgentPhone) : that.urgentPhone != null)
            return false;
        if (identityType != null ? !identityType.equals(that.identityType) : that.identityType != null)
            return false;
        if (identityCode != null ? !identityCode.equals(that.identityCode) : that.identityCode != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null)
            return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null)
            return false;
        if (authenticationTime != null ? !authenticationTime.equals(that.authenticationTime) : that
                .authenticationTime != null)
            return false;
        if (authStatus != null ? !authStatus.equals(that.authStatus) : that.authStatus != null)
            return false;
        if (propertyId != null ? !propertyId.equals(that.propertyId) : that.propertyId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tenantId;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (urgentName != null ? urgentName.hashCode() : 0);
        result = 31 * result + (urgentPhone != null ? urgentPhone.hashCode() : 0);
        result = 31 * result + (identityType != null ? identityType.hashCode() : 0);
        result = 31 * result + (identityCode != null ? identityCode.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (authenticationTime != null ? authenticationTime.hashCode() : 0);
        result = 31 * result + (authStatus != null ? authStatus.hashCode() : 0);
        result = 31 * result + (propertyId != null ? propertyId.hashCode() : 0);
        return result;
    }
}
