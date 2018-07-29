package estate.common.util;

import org.junit.Test;

import java.util.Random;

/**
 * Created by 应泽林 on 18-1-6.
 *
 */
public class VerifyCodeGenerate
{
    public static String create()
    {
        Random random=new Random();
        return String.valueOf(random.nextInt(899999)+100000);
    }


    @Test
    public void test()
    {
        create();
    }
}
