package estate.entity.json;

/**
 * Created by 应泽林 on 18-1-22.
 */
public class KindEditor
{
    private int error;
    private String url;
    private String message;

    public KindEditor(){}

    public KindEditor(int error)
    {
        this.error=error;
    }

    public int getError()
    {
        return error;
    }

    public void setError(int error)
    {
        this.error = error;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
