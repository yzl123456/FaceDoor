package estate.thirdApi.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by 应泽林 on 18-1-15.
 * 发送短信通知
 */
public class Message
{
    public static String send(String mobile, String content)
    {
        String sign="阳辰";
        StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");
        sb.append("name=VerPass@163.com");
        sb.append("&pwd=0C9E4B9FCDD8A770110444E6A7B8");
        sb.append("&mobile=").append(mobile);
        try
        {
            sb.append("&content=").append(URLEncoder.encode(content, "UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        try
        {
            sb.append("&sign=").append(URLEncoder.encode(sign, "UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        sb.append("&type=pt&extno=");
        URL url = null;
        try
        {
            url = new URL(sb.toString());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try
        {
            connection = (HttpURLConnection) url.openConnection();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            connection.setRequestMethod("POST");
        }
        catch (ProtocolException e)
        {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String inputline = null;
        try
        {
            inputline = in.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(inputline);
        String[] strings=inputline.split(",");
        switch (strings[0])
        {
            case "0":
                return "succ";
            case "1":
                return "含有敏感词汇";
            case "2":
                return "余额不足";
            case "3":
                return "请指定接受者电话";
            case "4":
                return "包含sql语句";
            case "10":
                return "账号不存在";
            default:
                return "未知错误";
        }
    }

    /**
     * 发送注册验证码
     * @param phone
     * @param verifyCode
     * @return
     */
    public static String sendRegisterVerifyCode(String phone,String verifyCode)
    {

        return "succ";
    }

    public static String sendFindPasswordVerifyCode(String phone,String verifyCode)
    {
        return "succ";
    }

}
