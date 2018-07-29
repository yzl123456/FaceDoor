package estate.entity.json;

/**
 * Created by 应泽林 on 18-1-27.
 * 基础的json实体类
 */
public class BasicJson
{
    protected boolean status=false;
    protected ErrMsg errorMsg=new ErrMsg();
    protected Object jsonString;

    public class ErrMsg
    {
        private String code=null;
        private String description=null;

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }
    }

    public BasicJson(boolean status)
    {
        this.status=status;
    }

    public BasicJson(){}

    public boolean getStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public Object getJsonString()
    {
        return this.jsonString;
    }

    public void setJsonString(Object object)
    {
        this.jsonString=object;
    }

    public ErrMsg getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(ErrMsg errorMsg)
    {
        this.errorMsg = errorMsg;
    }
}
