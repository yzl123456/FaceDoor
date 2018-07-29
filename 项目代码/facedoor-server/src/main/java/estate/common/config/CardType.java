package estate.common.config;

import estate.exception.TypeErrorException;

/**
 * Created by 应泽林 on 18-1-9.
 * 证件类型
 */
public class CardType
{
    public final static Byte IDCARD=0;
    public final static Byte SOLDIERCARD=1;

    public static boolean checkType(Byte type) throws TypeErrorException
    {
        if (!(type.equals(IDCARD)||type.equals(SOLDIERCARD)))
            throw new TypeErrorException("证件类型参数错误");
        return true;
    }
}
