package estate.exception;

/**
 * Created by 应泽林 on 18-1-8.
 * 物业没有绑定费用异常
 */
public class PropertyNotBindFeeItemException extends Exception
{
    public PropertyNotBindFeeItemException(){}

    public PropertyNotBindFeeItemException(String msg)
    {
        super(msg);
    }
}
