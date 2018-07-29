package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class FeeItemEntity
{
    protected Integer id;
    protected String name;
    protected String decription;
    protected Integer feeTypeId;
    protected Integer ruleId;
    protected Byte isPeriodic;
    protected Integer villageId;
    protected RuleEntity ruleEntity;
    protected String payStartTime;
    protected String payEndTime;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDecription()
    {
        return decription;
    }

    public void setDecription(String decription)
    {
        this.decription = decription;
    }

    public Integer getFeeTypeId()
    {
        return feeTypeId;
    }

    public void setFeeTypeId(Integer feeTypeId)
    {
        this.feeTypeId = feeTypeId;
    }

    public Integer getRuleId()
    {
        return ruleId;
    }

    public void setRuleId(Integer ruleId)
    {
        this.ruleId = ruleId;
    }

    public Byte getIsPeriodic()
    {
        return isPeriodic;
    }

    public void setIsPeriodic(Byte isPeriodic)
    {
        this.isPeriodic = isPeriodic;
    }

    public Integer getVillageId()
    {
        return villageId;
    }

    public void setVillageId(Integer villageId)
    {
        this.villageId = villageId;
    }

    public RuleEntity getRuleEntity()
    {
        return ruleEntity;
    }
    public void setRuleEntity(RuleEntity ruleEntity)
    {
        this.ruleEntity = ruleEntity;
    }



    public String getPayStartTime()
    {
        return payStartTime;
    }

    public void setPayStartTime(String payStartTime)
    {
        this.payStartTime = payStartTime;
    }

    public String getPayEndTime()
    {
        return payEndTime;
    }

    public void setPayEndTime(String payEndTime)
    {
        this.payEndTime = payEndTime;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
