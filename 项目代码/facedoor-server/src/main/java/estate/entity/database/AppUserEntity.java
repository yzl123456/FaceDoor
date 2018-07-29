package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class AppUserEntity
{
    private String phone;
    private String passwd;
    private String salt;
    private String nickname;
    private Byte status;
    private Long registerTime;
    private Long lastLogin;

    public Long getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getPasswd()
    {
        return passwd;
    }

    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Long getRegisterTime()
    {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime)
    {
        this.registerTime = registerTime;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }
}
