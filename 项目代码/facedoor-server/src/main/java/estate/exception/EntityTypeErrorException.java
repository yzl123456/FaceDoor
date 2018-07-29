package estate.exception;

/**
 * Created by 应泽林 on 18-1-22.
 */
public class EntityTypeErrorException extends Exception
{
    public EntityTypeErrorException(){}

    public EntityTypeErrorException(String msg)
    {
        super(msg);
    }
}
