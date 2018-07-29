package estate.common.util;

import com.google.gson.Gson;

/**
 * Created by 应泽林 on 18-1-6.
 *
 */
public class GsonUtil
{
    private static Gson gson;
    private GsonUtil(){}

    public static Gson getGson()
    {
        if (gson!=null)
            return gson;
        else
            return new Gson();
    }

}
