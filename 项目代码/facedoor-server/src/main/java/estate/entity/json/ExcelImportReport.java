package estate.entity.json;

import java.util.List;

/**
 * Created by 应泽林 on 18-1-24.
 * 导入excel后的结果记录类
 */
public class ExcelImportReport
{
    private Integer succNum=0;
    private Integer errorNum=0;
    private List<String> errorDescription;

    public Integer getSuccNum()
    {
        return succNum;
    }

    public void setSuccNum(Integer succNum)
    {
        this.succNum = succNum;
    }

    public Integer getErrorNum()
    {
        return errorNum;
    }

    public void setErrorNum(Integer errorNum)
    {
        this.errorNum = errorNum;
    }

    public List<String> getErrorDescription()
    {
        return errorDescription;
    }

    public void setErrorDescription(List<String> errorDescription)
    {
        this.errorDescription = errorDescription;
    }
}
