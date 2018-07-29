package estate.common.config;

import estate.exception.TypeErrorException;

/**
 * Created by 应泽林 on 18-1-9.
 *
 */
public class SexType
{
    public final static Byte MAN=0;
    public final static Byte WOMAN=1;

    public static boolean checkType(Byte type) throws TypeErrorException
    {
        if (!(type.equals(MAN)||type.equals(WOMAN)))
            throw new TypeErrorException("性别参数错误");
        return true;
    }
}
