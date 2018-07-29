package estate.exception;

/**
 * Created by 应泽林 on 18-2-4.
 * APP用户不存在的异常类
 */
public class AppUserNotExitException extends Exception
{
    public AppUserNotExitException(){}

    public AppUserNotExitException(String msg)
    {
        super(msg);
    }

}
