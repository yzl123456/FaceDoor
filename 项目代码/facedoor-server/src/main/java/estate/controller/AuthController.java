package estate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import estate.dao.ConsoleUserDao;
import estate.entity.database.ConsoleUserEntity;
import estate.entity.database.OwnerEntity;
import estate.entity.json.BasicJson;
import estate.service.BaseService;

/**
 * Created by 应泽林 on 18-1-6.
 *
 */
@RestController
@RequestMapping("/web/auth")
public class AuthController
{
    @Autowired
    private ConsoleUserDao consoleUserDao;

    @Autowired
    private BaseService baseService;
    
    //设置APPID/AK/SK
	//ps：这三个值需要在百度人脸识别中注册后获得
    public static final String APP_ID = "10872430";
    public static final String API_KEY = "kky2b3VNk6Yn41v9aIGaIUjS";
    public static final String SECRET_KEY = "l0ZjW7P4iMIceseaz66lROMnxYDOwAtT";
    
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BasicJson login(HttpServletRequest request) throws Exception
    {
    	System.out.println("this is login method----");
    	BasicJson basicJson=new BasicJson();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if (username==null||username.equals(""))
        {
            basicJson.getErrorMsg().setDescription("请输入用户名");
            return basicJson;
        }

        if (password==null||password.equals(""))
        {
            basicJson.getErrorMsg().setDescription("请输入密码");
            return basicJson;
        }

        ConsoleUserEntity consoleUserEntity=consoleUserDao.getConsoleUserByPhone(username);
        if (consoleUserEntity==null)
        {
            basicJson.getErrorMsg().setDescription("用户不存在");
            return basicJson;
        }
        if (!consoleUserEntity.getPassword().equals(password))
        {
            basicJson.getErrorMsg().setDescription("密码错误!");
            return basicJson;
        }

        basicJson.setStatus(true);
        request.getSession().setAttribute("user",consoleUserEntity);
        return basicJson;
    }
    
    @RequestMapping(value = "/registerMachine",method = RequestMethod.POST)
    public void registerMachine(HttpServletRequest request,OwnerEntity ownerEntity,MultipartFile face) throws Exception{
    	System.out.println("this is the machine-------------");
    	System.out.println(ownerEntity.getName());
    	System.out.println(ownerEntity.getAge());
    	System.out.println(ownerEntity.getSex());
    	System.out.println(ownerEntity.getPhone());
    	
    	String imgUrl=null;
        String kind=request.getParameter("kind");
        ownerEntity.setIdentityType((byte)0);//身份证
        if(kind==null)
        	return ;
        if(kind.equals("0")){
    		//获取文件原始名称
    		String originalFilename = face.getOriginalFilename();
    		//上传图片
    		if(face!=null && originalFilename!=null && originalFilename.length()>0){
    			//存储图片的物理路径
    			String apachePath=request.getSession().getServletContext().getRealPath("/")+"//view//img//face//owner//";
    			//新的图片名称
    			String newFileName = UUID.randomUUID()+ originalFilename.substring(originalFilename.lastIndexOf("."));
    			//新图片
    			imgUrl=apachePath+newFileName;
    			File newFile= new File(imgUrl);
    			//将内存中的数据写入磁盘
    			face.transferTo(newFile);
    			ownerEntity.setFaceURL(newFileName);
    			//		    userService.insertUser(user,newFileName);
    			System.out.println("ready to save in DB--------");
    			HttpSession session = request.getSession();
    			session.setAttribute("imgUrl", newFileName);
    			
    			//存储到数据库
    	    	baseService.save(ownerEntity);
    			
				/*
				 * IO流复制
				 */
				System.out.println("ready to copy..........");
				InputStream in=new FileInputStream(apachePath+newFileName);
	   			String pic_path = "D://j2ee_estate//estateOA-master//WebContent//view//img//face//owner//";
				OutputStream out=new FileOutputStream(new File(pic_path+newFileName));
				byte[] buffer=new byte[1024*10];
				int len=0;
				while((len=in.read(buffer))!=-1){
					out.write(buffer);
				}
				in.close();
				out.close();
    		}
    		
        	//注册到百度云人脸库
//            System.out.println("faceRecognize=====================人脸注册测试===========================");
//            HashMap<String, String> optionsAddUser = new HashMap<String, String>();
//            AipFaceDemo.facesetAddUser(AipFaceDemo.getClient(),imgUrl, ownerEntity.getName(),
//            		ownerEntity.getName()+"的个人信息", Arrays.asList("owner"), optionsAddUser);
    		
        	
        }
        else if(kind.equals("1")){
        	
        }
        else if(kind.equals("2")){
        	
        }
    	
    }
}
