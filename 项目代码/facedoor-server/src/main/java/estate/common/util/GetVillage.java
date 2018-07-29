package estate.common.util;

import estate.entity.json.BasicJson;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 应泽林 on 18-1-13.
 *
 */
public class GetVillage
{
    public static Integer get(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        Cookie[] cookies=request.getCookies();
        Integer villageID=null;
        for (Cookie cookie:cookies)
        {
            if (cookie.getName().equals("villageID"))
            {
                villageID = Integer.valueOf(cookie.getValue());
                break;
            }
        }
        if (villageID==null)
        {
            villageID= (Integer) request.getSession().getAttribute("villageID");
        }

        if (villageID==null)
        {
            BasicJson basicJson=new BasicJson();
            basicJson.getErrorMsg().setDescription("错误!\n未能获取到正确的园区信息\n请选择一个园区\n并且检查您的浏览器是否禁用cookie或者重新登陆系统");
            response.setContentType("application//json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(GsonUtil.getGson().toJson(basicJson));
        }

        return villageID;
    }
}
