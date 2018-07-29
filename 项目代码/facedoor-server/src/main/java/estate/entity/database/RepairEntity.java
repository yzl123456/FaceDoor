package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class RepairEntity
{
    private Integer id;
    private String phone;
    private Integer repairManId;
    private String title;
    private String content;
    private String description;
    private Long submitTime;
    private Long processTime;
    private Long finishTime;
    private String imageIdList;
    private Byte status;
    private String remark;
    private String remarkText;
    private Integer cuId;
    private String result;
    private ConsoleUserEntity consoleUserEntity;
    private RepairManEntity repairManEntity;


    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getImageIdList()
    {
        return imageIdList;
    }

    public void setImageIdList(String imageIdList)
    {
        this.imageIdList = imageIdList;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemarkText()
    {
        return remarkText;
    }

    public void setRemarkText(String remarkText)
    {
        this.remarkText = remarkText;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public ConsoleUserEntity getConsoleUserEntity()
    {
        return consoleUserEntity;
    }

    public void setConsoleUserEntity(ConsoleUserEntity consoleUserEntity)
    {
        this.consoleUserEntity = consoleUserEntity;
    }

    public Integer getCuId()
    {
        return cuId;
    }

    public void setCuId(Integer cuId)
    {
        this.cuId = cuId;
    }

    public Long getSubmitTime()
    {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime)
    {
        this.submitTime = submitTime;
    }

    public Long getProcessTime()
    {
        return processTime;
    }

    public void setProcessTime(Long processTime)
    {
        this.processTime = processTime;
    }

    public Long getFinishTime()
    {
        return finishTime;
    }

    public void setFinishTime(Long finishTime)
    {
        this.finishTime = finishTime;
    }

    public RepairManEntity getRepairManEntity()
    {
        return repairManEntity;
    }

    public void setRepairManEntity(RepairManEntity repairManEntity)
    {
        this.repairManEntity = repairManEntity;
    }

    public Integer getRepairManId()
    {
        return repairManId;
    }

    public void setRepairManId(Integer repairManId)
    {
        this.repairManId = repairManId;
    }
}
