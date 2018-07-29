package estate.exception;
/**
 * Created by 应泽林 on 18-1-22.
 */
public class UserTypeErrorException  extends Exception
{
    public UserTypeErrorException(){}

    public UserTypeErrorException(String msg)
    {
        super(msg);
    }
}
