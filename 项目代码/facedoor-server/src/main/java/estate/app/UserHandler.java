package estate.app;

import estate.common.config.AppUserStatus;
import estate.common.config.BindStatus;
import estate.common.config.UserType;
import estate.common.util.LogUtil;
import estate.common.util.VerifyCodeGenerate;
import estate.entity.database.AppUserEntity;
import estate.entity.database.PropertyEntity;
import estate.entity.database.PropertyOwnerInfoEntity;
import estate.entity.json.BasicJson;
import estate.service.BaseService;
import estate.service.PropertyOwnerService;
import estate.service.PropertyService;
import estate.service.UserService;
import estate.thirdApi.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by 应泽林 on 18-3-21.
 * 登陆,登出,注册(获取验证码,核对验证码,绑定业主)
 */
@RestController
@RequestMapping("api/uc")
public class UserHandler
{
    @Autowired
    private BaseService baseService;
    @Autowired
    private UserService userService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyOwnerService propertyOwnerService;

    /**
     * app用户登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    public BasicJson login(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        AppUserEntity appUserEntity= (AppUserEntity) baseService.get(phone,AppUserEntity.class);
        if (appUserEntity==null||appUserEntity.getStatus().equals(AppUserStatus.DELETE))
        {
            basicJson.getErrorMsg().setDescription("用户不存在");
            return basicJson;
        }
        //TODO 密码加盐验证
        if (!password.equals(appUserEntity.getPasswd()))
        {
            basicJson.getErrorMsg().setDescription("密码错误");
            return basicJson;
        }
        if (appUserEntity.getStatus().equals(AppUserStatus.DISABLE))
        {
            basicJson.getErrorMsg().setDescription("登录失败,该用户已被禁用");
            return basicJson;
        }

        basicJson.setStatus(true);
        request.getSession().setAttribute("phone", phone);
        basicJson.setJsonString(request.getSession().getId());
        return basicJson;
    }

    /**
     * 注销登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut",method = RequestMethod.GET)
    public BasicJson loginOut(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(true);
        request.getSession().removeAttribute("phone");
        return basicJson;
    }

    /**
     * 获取验证码,如果用户状态为删除或者不存在才允许注册
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping(value = "/register/getVerifyCode/{phone}",method = RequestMethod.GET)
    public BasicJson getVerifyCode(@PathVariable String phone,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        if (phone==null)
        {
            basicJson.getErrorMsg().setDescription("请输入手机号!");
            return basicJson;
        }
        AppUserEntity appUserEntity= (AppUserEntity) baseService.get(phone,AppUserEntity.class);
        if (appUserEntity!=null&&!appUserEntity.getStatus().equals(AppUserStatus.DELETE))
        {
            basicJson.getErrorMsg().setDescription("手机号码已注册");
            return basicJson;
        }
        String verifyCode=VerifyCodeGenerate.create();
        String msg= Message.sendRegisterVerifyCode(phone, verifyCode);
        if (!msg.equals("succ"))
        {
            basicJson.getErrorMsg().setDescription(msg);
            return basicJson;
        }

        LogUtil.E("verifycode:"+verifyCode);
        request.getSession().setAttribute("verifyCode", verifyCode);
        request.getSession().setAttribute("phone", phone);

        basicJson.setStatus(true);
        basicJson.setJsonString(request.getSession().getId());
        return basicJson;
    }

    /**
     * 注册核对验证码
     * @param request
     * @param verifyCode
     * @return
     */
    @RequestMapping(value = "/register/checkVerifyCode/{verifyCode}",method = RequestMethod.GET)
    public BasicJson checkVerifyCodeR(HttpServletRequest request,@PathVariable String verifyCode)
    {
        BasicJson basicJson=new BasicJson();
        if (verifyCode==null|| Objects.equals(verifyCode, ""))
        {
            basicJson.getErrorMsg().setDescription("请输入验证码");
            return basicJson;
        }
        if (!verifyCode.equals(request.getSession().getAttribute("verifyCode")))
        {
            LogUtil.E("session:"+request.getSession().getAttribute("verifyCode")+"  post:"+verifyCode);
            basicJson.getErrorMsg().setDescription("验证码输入错误!");
            return basicJson;
        }

        request.getSession().removeAttribute("verifyCode");
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 开始注册,如果该用户为业主的话,会自动绑定到名下的所有物业
     * @param request
     * @return
     */
    @RequestMapping(value = "/register/doRegister",method = RequestMethod.GET)
    public BasicJson regist(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        AppUserEntity appUserEntity=new AppUserEntity();
        String phone= (String) request.getSession().getAttribute("phone");
        String nickname=request.getParameter("nickname");
        String password=request.getParameter("password");

        appUserEntity.setPhone(phone);
        appUserEntity.setNickname(nickname);
        appUserEntity.setPasswd(password);
        appUserEntity.setRegisterTime(System.currentTimeMillis());
        appUserEntity.setStatus(AppUserStatus.ENABLE);
        Object owner;
        try
        {
            owner=userService.getUserInfoByPhoneRole(phone, UserType.OWNER);
            baseService.save(appUserEntity);
        }
        catch (Exception e)
        {
            LogUtil.E("错误:"+e.getMessage());
            basicJson.getErrorMsg().setDescription("注册失败");
            return basicJson;
        }
        if (owner==null)
        {
            basicJson.getErrorMsg().setCode("100001");
        }
        else
        {
            try
            {
                ArrayList<PropertyEntity> propertyEntities = propertyService.
                        getPropertyByPhoneRole(phone, UserType.OWNER);
                if (propertyEntities!=null)
                {
                    for (PropertyEntity propertyEntity : propertyEntities)
                    {
                        PropertyOwnerInfoEntity propertyOwnerInfoEntity = new PropertyOwnerInfoEntity();
                        propertyOwnerInfoEntity.setUserRole(UserType.OWNER);
                        propertyOwnerInfoEntity.setPhone(phone);
                        propertyOwnerInfoEntity.setPropertyId(propertyEntity.getId());
                        propertyOwnerInfoEntity.setStatus(BindStatus.CHECKED);
                        baseService.save(propertyOwnerInfoEntity);
                    }
                }
            }
            catch (Exception e)
            {
                basicJson.getErrorMsg().setDescription("注册成功,但绑定业主时出现错误");
                return basicJson;
            }
            basicJson.getErrorMsg().setCode("100000");
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 对于个人信息的一些修改操作
     * @param action
     * @param request
     * @return
     */
    @RequestMapping(value = "/modify/{action}")
    public BasicJson modify(@PathVariable String action,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        String phone= (String) request.getSession().getAttribute("phone");
        AppUserEntity appUserEntity= (AppUserEntity) baseService.get(phone, AppUserEntity.class);
        switch (action)
        {
            case "password":
                String oldPassword=request.getParameter("oldPassword");
                String newPassword=request.getParameter("newPassword");
                if (!oldPassword.equals(appUserEntity.getPasswd()))
                {
                    basicJson.getErrorMsg().setDescription("原密码错误");
                    return basicJson;
                }
                appUserEntity.setPasswd(newPassword);
                try
                {
                    baseService.save(appUserEntity);
                }
                catch (Exception e)
                {
                    LogUtil.E("保存用户信息失败"+e.getMessage());
                    basicJson.getErrorMsg().setDescription("修改密码失败");
                    return basicJson;
                }
                break;
//            case "getProfile":
//                try
//                {
//                    Object o=userService.getUserInfoByPhoneRole(phone, role);
//                    if (o==null)
//                    {
//                        basicJson.getErrorMsg().setDescription("获取用户信息失败");
//                        return basicJson;
//                    }
//                    basicJson.setJsonString(o);
//                }
//                catch (Exception e)
//                {
//                    basicJson.getErrorMsg().setDescription("获取用户信息出错");
//                    return basicJson;
//                }
//                break;
            default:
                basicJson.getErrorMsg().setDescription("请求路径错误!");
                return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 找回密码发送短信
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping(value = "/findPassword/{phone}",method = RequestMethod.GET)
    public BasicJson findPassword(@PathVariable String phone,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        String verifyCode=VerifyCodeGenerate.create();
        String msg=Message.sendFindPasswordVerifyCode(phone,verifyCode);
        if (!msg.equals("succ"))
        {
            basicJson.getErrorMsg().setDescription(msg);
            return basicJson;
        }
        request.getSession().setAttribute("phone", phone);
        request.getSession().setAttribute("verifyCode",verifyCode);

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 核对找回密码的验证码
     * @param verifyCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/findPassword/checkVerifyCode/{verifyCode}",method = RequestMethod.GET)
    public BasicJson checkVerifyCodeFD(@PathVariable String verifyCode,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        if (!verifyCode.equals(request.getSession().getAttribute("verifyCode")))
        {
            basicJson.getErrorMsg().setDescription("验证码错误");
            return basicJson;
        }

        request.getSession().setAttribute("findPassword","yes");
        request.getSession().removeAttribute("verifyCode");
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 重置密码
     * @return
     */
    @RequestMapping(value = "/findPassword/reset/{newPassword}",method = RequestMethod.GET)
    public BasicJson resetPassword(@PathVariable String newPassword,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        HttpSession httpSession=request.getSession();
        String phone= (String) httpSession.getAttribute("phone");
        String symbol= (String) httpSession.getAttribute("findPassword");
        if (!symbol.equals("yes"))
        {
            basicJson.getErrorMsg().setDescription("请先通过短信认证");
            return basicJson;
        }
        try
        {
            AppUserEntity appUserEntity= (AppUserEntity) baseService.get(phone, AppUserEntity.class);
            appUserEntity.setPasswd(newPassword);
            baseService.save(appUserEntity);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("修改失败,请退出重试");
            return basicJson;
        }
        httpSession.removeAttribute("findPassword");
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * app用户获取角色
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRole",method = RequestMethod.GET)
    public BasicJson getUserRole(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        try
        {
            String phone= (String) request.getSession().getAttribute("phone");
            basicJson.setJsonString(propertyOwnerService.getRoleStringByPhone(phone));
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取用户角色失败");
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }
}
