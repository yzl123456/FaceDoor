package estate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import estate.common.config.AppUserStatus;
import estate.common.config.UserType;
import estate.common.util.LogUtil;
import estate.entity.database.AppUserEntity;
import estate.entity.database.GuestEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.database.OwnerVerifyEntity;
import estate.entity.database.PropertyOwnerInfoEntity;
import estate.entity.json.BasicJson;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.AppUserNotExitException;
import estate.service.BaseService;
import estate.service.BlackManService;
import estate.service.GuestService;
import estate.service.OtherService;
import estate.service.OwnerVerifyService;
import estate.service.PictureService;
import estate.service.PropertyOwnerService;
import estate.service.PropertyService;
import estate.service.UserService;
import estate.service.WorkerService;

/**
 * Created by 应泽林 on 18-1-3.
 *
 */
@RestController
@RequestMapping("/web/user")
public class UserController
{	
    public static final String APP_ID = "10244589";
    public static final String API_KEY = "U2LSPNlqaDnYQ9l92L39GwcT";
    public static final String SECRET_KEY = "qdOjXwIbWnvgB9QVdQ06VUQrszLIQd5Y";
    @Autowired
    private UserService userService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private GuestService guestService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private OtherService otherService;
    @Autowired
    private OwnerVerifyService ownerVerifyService;
    @Autowired
    private PropertyOwnerService propertyOwnerService;
    @Autowired
    private BlackManService blackManService;

    @Autowired
    private PictureService pictureService;
    
    String imgUrl;
    /**
     * 获取业主列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/ownerList")
    public TableData getOwnerList(TableFilter tableFilter,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        if(request.getParameter("search[value]")!=null)//bootstrap的表格搜索条件
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");//搜索条件为空
        try
        {	
            return userService.getOwnerList(tableFilter);//调用userservice获取业主列表
        }
        catch (Exception e)
        {	
            TableData tableData=new TableData(false);
            tableData.getErrorMsg().setDescription(e.getMessage());//出错得到错误信息
            return tableData;
        }
    }
    /**
     * 获取访客列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/guestList")
    public TableData getGuestList(TableFilter tableFilter,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        if(request.getParameter("search[value]")!=null)//bootstrap的表格搜索条件
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");//搜索条件为空
        try
        {	
            return guestService.getGuestList(tableFilter);//调用guestService获取访客列表
        }
        catch (Exception e)
        {	
            TableData tableData=new TableData(false);
            tableData.getErrorMsg().setDescription(e.getMessage());
            return tableData;
        }
    }
    
    /**
     * 获取工作人员列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerList")
    public TableData getWorkerList(TableFilter tableFilter,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        if(request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");

        try
        {	
            return workerService.getWorkerList(tableFilter);
        }
        catch (Exception e)
        {	
            TableData tableData=new TableData(false);
            tableData.getErrorMsg().setDescription(e.getMessage());
            return tableData;
        }

    }
    
    /**
     * 获取其他人员列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/otherList")
    public TableData getOtherList(TableFilter tableFilter,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        if(request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");

        try
        {	
            return otherService.getOtherList(tableFilter);
        }
        catch (Exception e)
        {	
            TableData tableData=new TableData(false);
            tableData.getErrorMsg().setDescription(e.getMessage());
            return tableData;
        }

    }
    
    /**
     * 获取验证列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/ownerVerifyList")
    public TableData getOwnerVerifyList(TableFilter tableFilter,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        if(request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");

        try
        {	
            return ownerVerifyService.getOwnerVerifyList(tableFilter);
        }
        catch (Exception e)
        {	
            TableData tableData=new TableData(false);
            tableData.getErrorMsg().setDescription(e.getMessage());
            return tableData;
        }

    }
    
    /**
     * 获取黑名单列表
     * @param request
     * @return
     */    
    @RequestMapping(value = "/blackManList")
    public TableData getBlackManList(TableFilter tableFilter,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        if(request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");

        try
        {	
            return blackManService.getBlackManList(tableFilter);
        }
        catch (Exception e)
        {	
            TableData tableData=new TableData(false);
            tableData.getErrorMsg().setDescription(e.getMessage());
            return tableData;
        }

    }    
    
    /**
     * 通过验证
     * @param request
     * @return
     */
    @RequestMapping(value = "/acceptVerify")
    public BasicJson AcceptVerify(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        BasicJson basicJson=new BasicJson(false);
        try
        {	
        	int id=Integer.parseInt(request.getParameter("id"));
        	OwnerVerifyEntity object = (OwnerVerifyEntity) baseService.get(id, OwnerVerifyEntity.class);
        	if(object.getStatu().equals("待审核")){
        		object.setStatu("已通过");
        		baseService.save(object);
        	}
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            basicJson.getErrorMsg().setCode("21233210");
            basicJson.getErrorMsg().setDescription("获取物业信息失败\n错误详情:"+e.getMessage());
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;

    }
    /**
     * 拒绝验证
     * @param request
     * @return
     */
    @RequestMapping(value = "/refuseVerify")
    public BasicJson RefuseVerify(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        BasicJson basicJson=new BasicJson(false);
        System.out.println("-----------------------REFUSE METHOD");
        try
        {	
        	int id=Integer.parseInt(request.getParameter("id"));
        	OwnerVerifyEntity object = (OwnerVerifyEntity) baseService.get(id, OwnerVerifyEntity.class);
        	if(object.getStatu().equals("待审核")){
        		object.setStatu("已拒绝");
        		baseService.save(object);
        	}

        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            basicJson.getErrorMsg().setCode("21233210");
            basicJson.getErrorMsg().setDescription("获取物业信息失败\n错误详情:"+e.getMessage());
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;

    }
    
    
    /**
     * 增加业主信息
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/addOwner",method = RequestMethod.POST)
    public BasicJson addOwner(HttpServletRequest request,OwnerEntity ownerEntity,MultipartFile face)
    {	
    	BasicJson basicJson=new BasicJson();
    	System.out.println("before upload");
    	System.out.println(ownerEntity.getFaceURL());
    	
    	String imagePath=null;
    	String manName=null;
    	String detailInfo=null;
    	String userId=null;
    	String note=null;
    	List<String> groupList=null;
    	//上传人脸
    	try {
    		//获取文件原始名称
    		String originalFilename = face.getOriginalFilename();
    		//上传图片
    		if(face!=null && originalFilename!=null && originalFilename.length()>0){
    			//存储图片的物理路径
    			String pic_path = "D://j2ee_estate//estateOA-master//WebContent//view//img//face//owner//";
    			String apachePath=request.getSession().getServletContext().getRealPath("/")+"//view//img//face//owner//";
    			//新的图片名称
    			String newFileName = UUID.randomUUID()+ originalFilename.substring(originalFilename.lastIndexOf("."));
    			//新图片
    			File newFile= new File(apachePath+newFileName);
    			//将内存中的数据写入磁盘
    			face.transferTo(newFile);
    			ownerEntity.setFaceURL(newFileName);
    			//		    userService.insertUser(user,newFileName);
    			HttpSession session = request.getSession();
    			session.setAttribute("imgUrl", newFileName);
    			
    			
				/*
				 * IO流复制
				 */
				System.out.println("ready to copy..........");
				InputStream in=new FileInputStream(apachePath+newFileName);
				OutputStream out=new FileOutputStream(new File(pic_path+newFileName));
				byte[] buffer=new byte[1024*10];
				int len=0;
				while((len=in.read(buffer))!=-1){
					out.write(buffer);
				}
				in.close();
				out.close();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			basicJson.getErrorMsg().setDescription("绑定出错,请重试");
		}
    	baseService.save(ownerEntity);

    	
    	
    	
    	//通过百度人脸API向人脸数据库中注册人脸信息
//    	AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
//        // 可选：设置网络连接参数
//        //建立连接的超时时间（单位：毫秒
//        client.setConnectionTimeoutInMillis(2000);
//        //setSocketTimeoutInMillis
//        client.setSocketTimeoutInMillis(60000);
//        HashMap<String, String> optionsAddUser = new HashMap<String, String>();
//        //注册人脸api调用
//        /**
//         * @param imagePath 图片路径
//         * @param userId 用户id
//         * @param note  用户资料
//         * @param groupList 用户组
//         * @param options 返回参数
//         */
//        client.addUser(userId, note, groupList, imagePath, optionsAddUser);
		  
        return basicJson;
    }


    public static File uploadFilesSpecifyPath(HttpServletRequest request, String fileDir, String Filedata) throws Exception {  
        MultipartFile mf = null;  
        File mff = null;  
        if (!(request instanceof MultipartHttpServletRequest)) {  
            String errorMsg = "your post form is not support ENCTYPE='multipart/form-data' ";  
      
            throw new RuntimeException(errorMsg);  
        }  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
        List<MultipartFile> multipartFiles = multipartRequest.getFiles(Filedata);  
        if (null != multipartFiles && multipartFiles.size() > 0) {  
            mf = multipartFiles.get(0);  
            String realPath = mergeStringWithSeparator(fileDir,mf.getOriginalFilename());  
            File source = new File(realPath);  
            try {  
                mf.transferTo(source);  
                mff = source;  
            } catch (Exception e) {  
                String errorMsg = "Upload file " + source.getAbsoluteFile() + " Error!" + e.getMessage();  
                throw new RuntimeException(errorMsg);  
            }  
        }  
        return mff;  
    }  
    
    public static String mergeStringWithSeparator(String...args){  
        StringBuilder sb=new StringBuilder();  
        for (String arg : args) {  
            sb.append(arg);  
            sb.append(File.separator);  
        }  
              
        return sb.substring(0, sb.length()-1).toString();  
    }  
    /**
     * 通过用户的电话和类型返回该用户关联的所有物业信息
     * @param phone
     * @return
     */
    @RequestMapping(value = "/{userRole}/getPropertiesByPhone/{phone}",method = RequestMethod.GET)
    public BasicJson getPropertiesByOwnerId(@PathVariable String userRole,@PathVariable String phone)
    {
        BasicJson basicJson=new BasicJson(false);
        try
        {
            switch (userRole)
            {
                case "owner":
                	System.out.println("666666");
                    basicJson.setJsonString(propertyService.getPropertyByPhoneRole(phone, UserType.OWNER));
                    break;
                case "appuser":
                    basicJson.setJsonString(propertyOwnerService.getByPhone(phone));
                    break;
                default:
                    basicJson.getErrorMsg().setDescription("用户类型参数错误");
                    return basicJson;
            }
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            basicJson.getErrorMsg().setCode("21233210");
            basicJson.getErrorMsg().setDescription("获取物业信息失败\n错误详情:"+e.getMessage());
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 通过用户类型和电话删除用户
     * 本来应该通过id删除的,但是物业业主信息表是通过业主的电话关联
     * @param userType
     * @param phone
     * @return
     */
    @RequestMapping(value = "/{userType}/delete/{phone}")
    public BasicJson deleteOwner(@PathVariable String userType,@PathVariable String phone)
    {
        BasicJson basicJson=new BasicJson();
        System.out.println("this is deeleteOwner method---");
        String groupName="";
        byte type;
        switch (userType)
        {
            case "owner":
                type=UserType.OWNER;
                groupName="owner";
                break;
            case "guest":
            	type=UserType.GUEST;
            	groupName="guest";
            	break;
            case "appuser":
                type=UserType.APPUSER;
                break;
            default:
                basicJson.getErrorMsg().setDescription("请求参数错误!");
                return basicJson;
        }
        try
        {
            //如果用户类型是业主的话,需要判断该业主是否绑定物业,若绑定了则不允许删除
            if (type==UserType.OWNER)
            {
                ArrayList<PropertyOwnerInfoEntity> propertyOwnerInfoEntities= propertyOwnerService.getByPhone(phone);
                if (propertyOwnerInfoEntities!=null)
                {
                    for (PropertyOwnerInfoEntity propertyOwnerInfoEntity : propertyOwnerInfoEntities)
                    {
                        if (propertyOwnerInfoEntity.getUserRole() == UserType.OWNER)
                        {
                            basicJson.getErrorMsg().setDescription("该业主已绑定物业,不能删除");
                            return basicJson;
                        }
                    }
                }
            }
            else if(type==UserType.GUEST){
            	int id=Integer.parseInt(phone);
            	GuestEntity object = (GuestEntity) baseService.get(id, GuestEntity.class);
            	baseService.delete(object);
            	
            }
            else if(type==UserType.WORKER){
            	
            }
            else if(type==UserType.OTHER){
            	
            }

            if(type==UserType.OWNER){
	            OwnerEntity owner = (OwnerEntity) userService.getUserInfoByPhoneRole(phone, UserType.OWNER);
	            //删除百度人脸库
	            //删除业主的线程
//	            new java.lang.Thread(new Runnable() {
//					@Override
//					public void run() {
//			            System.out.println("faceRecognize=====================人脸删除测试===========================");
//			            AipFaceDemo.facesetDeleteUser(AipFaceDemo.getClient(), owner.getName(), Arrays.asList("owner"));
//					}
//				}).start();
	
	            System.out.println("ready to delete owner");
	            userService.deleteOwner(phone);
            }
            
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            basicJson.getErrorMsg().setCode("21233210");
            basicJson.getErrorMsg().setDescription("查询失败\n错误详情:"+e.getMessage());
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 获取认证用户的列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/authenticatedUserList")
    public TableData getTenantList(TableFilter tableFilter,HttpServletRequest request)
    {
        if(request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");

        try
        {
            return userService.getAuthenticatedUserList(tableFilter);
        }
        catch (Exception e)
        {
//            LogUtil.E(e.getClass()+e.getMessage());
            return null;
        }

    }

    /**
     * 获取租客列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/tenantList")
    public TableData getAuthList(TableFilter tableFilter,HttpServletRequest request)
    {
        if(request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");

        try
        {
            return userService.getTenantList(tableFilter);
        }
        catch (Exception e)
        {
//            LogUtil.E(e.getClass()+e.getMessage());
            return null;
        }
    }

    /**
     * 获取APP用户列表
     * @param tableFilter
     * @param request
     * @return
     */
    @RequestMapping(value = "/appUserList")
    public TableData getAppUserList(TableFilter tableFilter,HttpServletRequest request)
    {
        if(request.getParameter("search[value]")!=null)
            tableFilter.setSearchValue(request.getParameter("search[value]"));
        else
            tableFilter.setSearchValue("");
        try
        {
            return userService.getAppUserList(tableFilter);
        }
        catch (Exception e)
        {
            LogUtil.E(e.getMessage());
            return null;
        }
    }
    
    @ResponseBody  
    @RequestMapping(value="/owner/edit")  
    public Map<String, Object> editOwner(HttpServletRequest request){  
        //从会话中获取用户信息  
        HttpSession session =  request.getSession();  
        int id=Integer.parseInt(request.getParameter("ID"));
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String phone=request.getParameter("phone");
        String identityType=request.getParameter("identityType");
        String identityCode=request.getParameter("identityCode");
        
        OwnerEntity owner=(OwnerEntity) baseService.get(id, OwnerEntity.class);
        owner.setName(name);
        if(sex.equals("1"))
        	owner.setSex((byte) 0);
        else
        	owner.setSex((byte) 1);
        owner.setPhone(phone);
        System.out.println(identityType+"------------------------");
        if(identityType.equals("1"))
        	owner.setIdentityType((byte)0);
        else
        	owner.setIdentityType((byte)1);
        
        owner.setIdentityCode(identityCode);
        
        
        baseService.save(owner);
        Map<String,Object> result = new HashMap<String, Object>();  
        result.put("statu", "success");  
 
        return result;  
    }  
    

    @RequestMapping(value = "/getUserInfoByPhone/{phone}")
    public BasicJson getUserDetailByPhone(@PathVariable String phone,HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();

        try
        {
            basicJson.setJsonString(userService.getUserDetailByPhone(phone));
        }
        catch (AppUserNotExitException e)
        {
            basicJson.getErrorMsg().setDescription(e.getMessage());
            return basicJson;
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取详细信息出错");
            return basicJson;
        }

        basicJson.setStatus(true);
        return basicJson;
    }

    /**
     * 改变appuser的状态,是否禁用该用户.
     * @param request
     * @return
     */
    @RequestMapping(value = "/appUserStatus" ,method = RequestMethod.POST)
    public BasicJson disableAppUser(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson(false);
        String phone=request.getParameter("phone");
        String statusStr=request.getParameter("status");
        Byte status;
        switch (statusStr)
        {
            case "enable":
                status = AppUserStatus.ENABLE;
                break;
            case "disable":
                status = AppUserStatus.DISABLE;
                break;
            default:
                basicJson.getErrorMsg().setDescription("参数错误!");
                return basicJson;
        }
        if (phone==null)
        {
            basicJson.getErrorMsg().setCode("0");
            basicJson.getErrorMsg().setDescription("参数错误!");
            return basicJson;
        }
        else
        {
            try
            {
                AppUserEntity appUserEntity= (AppUserEntity) baseService.get(phone, AppUserEntity.class);
                appUserEntity.setStatus(status);
                baseService.save(appUserEntity);
            }
            catch (Exception e)
            {
                basicJson.getErrorMsg().setCode(e.getMessage());
                basicJson.getErrorMsg().setDescription("操作失败");
                return basicJson;
            }
        }
        basicJson.setStatus(true);
        return basicJson;
    }

}
