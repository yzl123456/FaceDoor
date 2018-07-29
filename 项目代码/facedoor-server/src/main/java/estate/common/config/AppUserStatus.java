package estate.common.config;

import estate.exception.TypeErrorException;

/**
 * Created by 应泽林 on 18-1-9.
 *
 */
public class AppUserStatus
{
    public final static Byte DELETE=-1;
    public final static Byte DISABLE=0;
    public final static Byte ENABLE=1;

    public static boolean checkType(Byte type) throws TypeErrorException
    {
        if (!(type.equals(DISABLE)||type.equals(ENABLE)||type.equals(DELETE)))
            throw new TypeErrorException("用户状态参数错误");
        return true;
    }
}
