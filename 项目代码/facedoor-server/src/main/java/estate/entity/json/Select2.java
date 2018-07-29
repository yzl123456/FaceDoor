package estate.entity.json;

/**
 * Created by 应泽林 on 18-2-26.
 * 该类为适应前段select2插件而编写的实体类
 */
public class Select2
{
    private String id;
    private String text;
    public Select2(String id,String text)
    {
        this.id=id;
        this.text=text;
    }
    public Select2(){}

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
