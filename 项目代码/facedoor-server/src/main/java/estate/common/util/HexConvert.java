package estate.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import estate.entity.json.BasicJson;
import estate.entity.json.Select2;
import org.junit.Test;

import java.util.List;

/**
 * Created by 应泽林 on 18-1-7.
 * 16进制和字符串转换工具
 */
public class HexConvert
{
    /**
     * 将字符串转为16进制
     * @param rawString 字符串
     * @return 16进制字符串
     */
    public static String toHexString(String rawString)
    {
        String output="";
        for (int i=0;i<rawString.length();i++)
        {
            int ch=(int)rawString.charAt(i);
            String hexPart=Integer.toHexString(ch);
            output+=hexPart;
        }
        return output;
    }

    /**
     * 将16进制转为字符串
     * @param hexString 16进制
     * @return utf-8编码后的字符串
     */
    public static String toRawString(String hexString)
    {
        byte[] baKeyword = new byte[hexString.length()/2];
        for(int i = 0; i < baKeyword.length; i++)
        {
            try
            {
                baKeyword[i] = (byte)(0xff & Integer.parseInt(hexString.substring(i*2, i*2+2),16));
            }
            catch(Exception ignore){}
        }
        try
        {
            hexString = new String(baKeyword, "UTF-8");
        }
        catch (Exception ignore){}
        return hexString;
    }

    @Test
    public void test()
    {
        Gson gson=new Gson();
        String s= "{\"status\":true,\"errorMsg\":{\"code\":null,\"description\":null},\"jsonString\":[{\"id\":\"1\"," +
                "\"text\":\"半山蓝湾-2栋-17号\"},{\"id\":\"2\",\"text\":\"半山蓝湾-2栋-27号\"},{\"id\":\"3\"," +
                "\"text\":\"location1\"},{\"id\":\"4\",\"text\":\"location1\"},{\"id\":\"5\",\"text\":\"location1\"}," +
                "{\"id\":\"6\",\"text\":\"location1\"},{\"id\":\"7\",\"text\":\"的撒旦\"},{\"id\":\"8\"," +
                "\"text\":\"抚琴西路199号1栋1单元10号\"},{\"id\":\"9\",\"text\":\"抚琴西路199号1栋1单元9号\"},{\"id\":\"10\"," +
                "\"text\":\"抚琴西路199号1栋1单元8号\"},{\"id\":\"11\",\"text\":\"抚琴西路199号1栋1单元6好\"}]}";

        BasicJson basicJson=gson.fromJson(s,BasicJson.class);
        String temp=gson.toJson(basicJson.getJsonString());
        List<Select2> options=gson.fromJson(temp,new TypeToken<List<Select2>>(){}.getType());
        for (Select2 select2:options)
        {
            LogUtil.E(select2.getId());
        }

//        LogUtil.E(toRawString("64686466676664"));
    }


}
