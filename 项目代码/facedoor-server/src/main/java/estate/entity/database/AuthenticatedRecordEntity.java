package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class AuthenticatedRecordEntity
{
    private int id;
    private String applicant;
    private Long applicationTime;
    private Byte applicationType;
    private Integer authId;
    private Byte authResult;
    private Long authTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getApplicant()
    {
        return applicant;
    }

    public void setApplicant(String applicant)
    {
        this.applicant = applicant;
    }

    public Long getApplicationTime()
    {
        return applicationTime;
    }

    public void setApplicationTime(Long applicationTime)
    {
        this.applicationTime = applicationTime;
    }

    public Byte getApplicationType()
    {
        return applicationType;
    }

    public void setApplicationType(Byte applicationType)
    {
        this.applicationType = applicationType;
    }

    public Integer getAuthId()
    {
        return authId;
    }

    public void setAuthId(Integer authId)
    {
        this.authId = authId;
    }

    public Byte getAuthResult()
    {
        return authResult;
    }

    public void setAuthResult(Byte authResult)
    {
        this.authResult = authResult;
    }

    public Long getAuthTime()
    {
        return authTime;
    }

    public void setAuthTime(Long authTime)
    {
        this.authTime = authTime;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AuthenticatedRecordEntity that = (AuthenticatedRecordEntity) o;

        if (id != that.id)
            return false;
        if (applicant != null ? !applicant.equals(that.applicant) : that.applicant != null)
            return false;
        if (applicationTime != null ? !applicationTime.equals(that.applicationTime) : that.applicationTime != null)
            return false;
        if (applicationType != null ? !applicationType.equals(that.applicationType) : that.applicationType != null)
            return false;
        if (authId != null ? !authId.equals(that.authId) : that.authId != null)
            return false;
        if (authResult != null ? !authResult.equals(that.authResult) : that.authResult != null)
            return false;
        if (authTime != null ? !authTime.equals(that.authTime) : that.authTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (applicant != null ? applicant.hashCode() : 0);
        result = 31 * result + (applicationTime != null ? applicationTime.hashCode() : 0);
        result = 31 * result + (applicationType != null ? applicationType.hashCode() : 0);
        result = 31 * result + (authId != null ? authId.hashCode() : 0);
        result = 31 * result + (authResult != null ? authResult.hashCode() : 0);
        result = 31 * result + (authTime != null ? authTime.hashCode() : 0);
        return result;
    }
}
