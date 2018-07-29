package estate.common.config;

import estate.exception.TypeErrorException;

/**
 * Created by 应泽林 on 18-1-24.
 *
 */
public class UserType
{
    public static final byte FAMILY=1;
    public static final byte TENANT=2;
    public static final byte OWNER=3;
    public static final byte APPUSER=4;
    public static final byte GUEST=5;
    public static final byte WORKER=6;
    public static final byte OTHER=7;
    public static final byte NOROLE=0;

    public static boolean checkType(byte type) throws TypeErrorException
    {
        if (!(type==FAMILY||type==TENANT))
            throw new TypeErrorException("用户类型非法");
        return true;
    }

}
