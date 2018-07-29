package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class AuthenticatedUserEntity
{
    private int auId;
    private String name;
    private String identityId;
    private String ownerRelationship;
    private Long birthday;
    private Long registerTime;
    private Byte sex;
    private Long boundTime;
    private Byte isBound;
    private String familycol;
    private Integer ownerId;
    private Integer userId;

    public int getAuId()
    {
        return auId;
    }

    public void setAuId(int auId)
    {
        this.auId = auId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIdentityId()
    {
        return identityId;
    }

    public void setIdentityId(String identityId)
    {
        this.identityId = identityId;
    }

    public String getOwnerRelationship()
    {
        return ownerRelationship;
    }

    public void setOwnerRelationship(String ownerRelationship)
    {
        this.ownerRelationship = ownerRelationship;
    }

    public Long getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Long birthday)
    {
        this.birthday = birthday;
    }

    public Long getRegisterTime()
    {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime)
    {
        this.registerTime = registerTime;
    }

    public Byte getSex()
    {
        return sex;
    }

    public void setSex(Byte sex)
    {
        this.sex = sex;
    }

    public Long getBoundTime()
    {
        return boundTime;
    }

    public void setBoundTime(Long boundTime)
    {
        this.boundTime = boundTime;
    }

    public Byte getIsBound()
    {
        return isBound;
    }

    public void setIsBound(Byte isBound)
    {
        this.isBound = isBound;
    }

    public String getFamilycol()
    {
        return familycol;
    }

    public void setFamilycol(String familycol)
    {
        this.familycol = familycol;
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId = ownerId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AuthenticatedUserEntity that = (AuthenticatedUserEntity) o;

        if (auId != that.auId)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (identityId != null ? !identityId.equals(that.identityId) : that.identityId != null)
            return false;
        if (ownerRelationship != null ? !ownerRelationship.equals(that.ownerRelationship) : that.ownerRelationship !=
                null)
            return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null)
            return false;
        if (registerTime != null ? !registerTime.equals(that.registerTime) : that.registerTime != null)
            return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null)
            return false;
        if (boundTime != null ? !boundTime.equals(that.boundTime) : that.boundTime != null)
            return false;
        if (isBound != null ? !isBound.equals(that.isBound) : that.isBound != null)
            return false;
        if (familycol != null ? !familycol.equals(that.familycol) : that.familycol != null)
            return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = auId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (identityId != null ? identityId.hashCode() : 0);
        result = 31 * result + (ownerRelationship != null ? ownerRelationship.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (boundTime != null ? boundTime.hashCode() : 0);
        result = 31 * result + (isBound != null ? isBound.hashCode() : 0);
        result = 31 * result + (familycol != null ? familycol.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
