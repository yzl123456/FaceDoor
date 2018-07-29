package estate.filter.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 应泽林 on 18-1-6.
 * 过滤器检查
 */
public class FilterService
{

    public static boolean isLogin(HttpServletRequest request,HttpServletResponse response)
    {
        HttpSession session=request.getSession();
        if (session==null)
            return false;
        if (session.getAttribute("username").equals("")||session.getAttribute("username")==null)
            return false;

        return true;
    }

    public static boolean isAuth()
    {
        return true;
    }
}
