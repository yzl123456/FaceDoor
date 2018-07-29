package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class RuleEntity
{
    private Integer ruleId;
    private String unitPrice;
    private String unit;
    private String description;
    private String overdueUnitPrice;
    private String overdueUnit;
    private Long startTime;
    private Long endTime;

    public Integer getRuleId()
    {
        return ruleId;
    }

    public void setRuleId(Integer ruleId)
    {
        this.ruleId = ruleId;
    }

    public String getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getOverdueUnitPrice()
    {
        return overdueUnitPrice;
    }

    public void setOverdueUnitPrice(String overdueUnitPrice)
    {
        this.overdueUnitPrice = overdueUnitPrice;
    }

    public String getOverdueUnit()
    {
        return overdueUnit;
    }

    public void setOverdueUnit(String overdueUnit)
    {
        this.overdueUnit = overdueUnit;
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


}
