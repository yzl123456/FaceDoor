package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class SsidSecretEntity
{
    private Integer id;
    private Integer controlId;
    private Byte controlType;
    private String symbol;
    private Integer villageId;
    private String secret;
    private String password;
    private Byte type;


    public Integer getControlId()
    {
        return controlId;
    }

    public void setControlId(Integer controlId)
    {
        this.controlId = controlId;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Byte getType()
    {
        return type;
    }

    public void setType(Byte type)
    {
        this.type = type;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getVillageId()
    {
        return villageId;
    }

    public void setVillageId(Integer villageId)
    {
        this.villageId = villageId;
    }

    public Byte getControlType()
    {
        return controlType;
    }

    public void setControlType(Byte controlType)
    {
        this.controlType = controlType;
    }
}
