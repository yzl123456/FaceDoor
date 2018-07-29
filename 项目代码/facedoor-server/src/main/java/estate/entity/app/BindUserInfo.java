package estate.entity.app;

import estate.entity.database.AppUserEntity;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class BindUserInfo
{
    private Integer bindId;
    private Byte role;
    private Byte status;
    private AppUserEntity appUserEntity;

    public Byte getRole()
    {
        return role;
    }

    public void setRole(Byte role)
    {
        this.role = role;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }

    public AppUserEntity getAppUserEntity()
    {
        return appUserEntity;
    }

    public void setAppUserEntity(AppUserEntity appUserEntity)
    {
        this.appUserEntity = appUserEntity;
    }

    public Integer getBindId()
    {
        return bindId;
    }

    public void setBindId(Integer bindId)
    {
        this.bindId = bindId;
    }
}
