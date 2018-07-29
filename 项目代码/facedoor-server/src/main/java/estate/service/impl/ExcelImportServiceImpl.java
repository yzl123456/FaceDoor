package estate.service.impl;

import com.google.gson.Gson;
import estate.common.Config;
import estate.common.config.*;
import estate.common.excelDefine.BindHead;
import estate.common.excelDefine.PropertyHead;
import estate.common.excelDefine.SecretHead;
import estate.common.util.Convert;
import estate.common.util.Validator;
import estate.dao.*;
import estate.entity.database.*;
import estate.entity.json.ExcelImportReport;
import estate.service.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("excelImportService")
public class ExcelImportServiceImpl implements ExcelImportService
{

    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private PropertyOwnerInfoDao propertyOwnerInfoDao;
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private BuildingDao buildingDao;
    @Autowired
    private UserDao userDao;


    @Override
    public ExcelImportReport importProperty(List<Map<String, String>> result)
    {
        ExcelImportReport excelImportReport=new ExcelImportReport();
        Integer errorNum=0;
        Integer succNum=0;
        boolean check;
        Gson gson=new Gson();
        List<String> errorDescription=new ArrayList<>();
        for (Map<String, String> map : result)
        {
            check=true;
            PropertyEntity propertyEntity=new PropertyEntity();

            //设置状态
            if (check)
            {
                String status=map.get(PropertyHead.STATUS);
                switch (status)
                {
                    case "出租":
                        propertyEntity.setStatus(PropertyStatus.CHUZU);
                        break;
                    case "自用":
                        propertyEntity.setStatus(PropertyStatus.SELF);
                        break;
                    default:
                        errorNum += 1;
                        errorDescription.add("物业状态不合法: <br/>" + gson.toJson(map));
                        check = false;
                        break;
                }
            }

            //设置编号
            if (check)
            {
                PropertyEntity propertyEntity1=propertyDao.getByCode(map.get(PropertyHead.CODE));
                if (propertyEntity1!=null)
                {
                    errorNum+=1;
                    errorDescription.add("该物业编号已存在: <br/>"+gson.toJson(map));
                    check=false;
                }
                else
                {
                    propertyEntity.setCode(map.get(PropertyHead.CODE));
                }
            }

            //设置地址信息
            if (check)
            {
                String location=map.get(PropertyHead.ADDRESS);
                if (!location.equals(""))
                    propertyEntity.setLocation(location);
                else
                {
                    errorNum+=1;
                    errorDescription.add("地址信息不能为空: <br/>"+gson.toJson(map));
                    check=false;
                }
            }

            //设置面积
            if (check)
            {
                try
                {
                    String squre=map.get(PropertyHead.SQURE);
                    BigDecimal bigDecimal=new BigDecimal(squre).setScale(2,BigDecimal.ROUND_HALF_UP);
                    propertyEntity.setPropertySquare(bigDecimal);
                }
                catch (Exception e)
                {
                    errorNum+=1;
                    errorDescription.add("房屋面积信息不合法: <br/>"+gson.toJson(map));
                    check=false;
                }
            }

            // 设置楼栋和楼栋关联
            if (check)
            {
                BuildingEntity buildingEntity = buildingDao.getByCode(map.get(PropertyHead.BUILDINGCODE));
                if (buildingEntity == null)
                {
                    errorNum += 1;
                    errorDescription.add("该楼栋编号不存在: <br/>" + gson.toJson(map));
                    check = false;
                }
                else
                {
                    propertyEntity.setVillageId(buildingEntity.getVillageId());
                    propertyEntity.setBuildingId(buildingEntity.getId());
                }
            }

            //设置物业类型
            if (check)
            {
                if (map.get(PropertyHead.TYPE).equals("商户"))
                    propertyEntity.setType(PropertyType.SHOP);
                else if (map.get(PropertyHead.TYPE).equals("住宅"))
                    propertyEntity.setType(PropertyType.APPARTEMENT);
                else
                {
                    errorNum += 1;
                    errorDescription.add("物业类型错误: <br/>" + gson.toJson(map));
                    check = false;
                }
            }

            //通过校验后的保存操作
            if (check)
            {
                try
                {
                    propertyEntity.setOpenDoorStatus(Config.TRUE);
                    propertyEntity.setModifyTime(System.currentTimeMillis());
                    baseDao.save(propertyEntity);
                    succNum+=1;
                }
                catch (Exception e)
                {
                    errorNum+=1;
                    errorDescription.add("插入数据库失败: <br/>"+gson.toJson(map));
                }
            }
        }
        excelImportReport.setErrorNum(errorNum);
        excelImportReport.setErrorDescription(errorDescription);
        excelImportReport.setSuccNum(succNum);
        return excelImportReport;
    }

    @Override
    public ExcelImportReport importBind(List<Map<String, String>> result)
    {
        ExcelImportReport excelImportReport=new ExcelImportReport();
        Integer errorNum=0;
        Integer succNum=0;
        boolean check;
        Gson gson=new Gson();
        List<String> errorDescription=new ArrayList<>();
        for (Map<String,String> map:result)
        {

            check=true;
            OwnerEntity ownerEntity=new OwnerEntity();

            //基本的数据检验
            if (Validator.isMobile(map.get(BindHead.PHONE)))
            {
                ownerEntity.setPhone(map.get(BindHead.PHONE));
            }
            else
            {
                errorNum += 1;
                errorDescription.add("手机号码不合法: <br/>" + gson.toJson(map));
                check = false;
            }
            ownerEntity.setName(map.get(BindHead.NAME));
            if (check)
            {
                if (map.get(BindHead.SEX).equals("男"))
                    ownerEntity.setSex(Sex.MAN);
                else if (map.get(BindHead.SEX).equals("女"))
                    ownerEntity.setSex(Sex.WOMAN);
                else
                {
                    errorNum += 1;
                    errorDescription.add("性别不合法: <br/>" + gson.toJson(map));
                    check = false;
                }
            }
            if (check)
            {
                Long birthday=Convert.time2num(map.get(BindHead.BIRTHDAY));
                ownerEntity.setBirthday(birthday);
                if (birthday==null)
                {
                    errorNum += 1;
                    errorDescription.add("出生日期不合法: <br/>" + gson.toJson(map));
                    check = false;
                }
            }
            ownerEntity.setUrgentName(map.get(BindHead.URGENTNAME));
            if (check)
            {
                if (!map.get(BindHead.URGENTPHONE).equals(""))
                {
                    if (Validator.isMobile(map.get(BindHead.URGENTPHONE)))
                    {
                        ownerEntity.setUrgentPhone(map.get(BindHead.URGENTPHONE));
                    }
                    else
                    {
                        errorNum += 1;
                        errorDescription.add("紧急联系人手机号码不合法: <br/>" + gson.toJson(map));
                        check = false;
                    }
                }
            }
            if (check)
            {
                String idTypeString=map.get(BindHead.IDTYPE);
                switch (idTypeString)
                {
                    case "身份证":
                        ownerEntity.setIdentityType(CardType.IDCARD);
                        break;
                    case "军官证":
                        ownerEntity.setIdentityType(CardType.SOLDIERCARD);
                        break;
                    default:
                        errorNum += 1;
                        errorDescription.add("证件类型不合法: <br/>" + gson.toJson(map));
                        check = false;
                        break;
                }
            }
            if (check)
            {
                if (Validator.isIDCard(map.get(BindHead.IDCODE)))
                {
                    ownerEntity.setIdentityCode(map.get(BindHead.IDCODE));
                }
                else
                {
                    errorNum += 1;
                    errorDescription.add("证件号码不合法: <br/>" + gson.toJson(map));
                    check = false;
                }
            }
            if (check)
            {
                Long authTime=Convert.time2num(map.get(BindHead.AUTHTIME));
                ownerEntity.setAuthenticationTime(authTime);
            }

            //检查用户绑定的每一个物业编号以及该绑定关系是否存在
            if (check)
            {
                try
                {
                    String propertyCodeList = map.get(BindHead.PROPERTYCODELIST);
                    for (String propertyCode : Arrays.asList(propertyCodeList.split(";")))
                    {
                        PropertyEntity propertyEntity = propertyDao.getByCode(propertyCode);
                        if (propertyEntity == null)
                        {
                            errorNum += 1;
                            errorDescription.add("该物业编号不存在: <br/>" + gson.toJson(map));
                            check = false;
                            break;
                        }
                        else if (propertyOwnerInfoDao.
                                getByPhonePropertyID(ownerEntity.getPhone(), propertyEntity.getId())!=null)
                        {
                            errorNum += 1;
                            errorDescription.add("该用户已和该物业绑定: <br/>" + gson.toJson(map));
                            check = false;
                            break;
                        }
                    }
                }
                catch (Exception e)
                {
                    errorNum += 1;
                    errorDescription.add("非法的物业编号: <br/>" + gson.toJson(map));
                    check = false;
                }
            }

            //经过检查后将用户信息和绑定信息写入数据库
            if (check)
            {
                try
                {
                    OwnerEntity ownerEntity1 = (OwnerEntity) userDao.getUserInfoByPhoneRole(ownerEntity.getPhone(), UserType.OWNER);
                    if (ownerEntity1 != null)
                    {
                        ownerEntity1.setName(ownerEntity.getName());
                        ownerEntity1.setSex(ownerEntity.getSex());
                        ownerEntity1.setBirthday(ownerEntity.getBirthday());
                        ownerEntity1.setIdentityType(ownerEntity.getIdentityType());
                        ownerEntity1.setIdentityCode(ownerEntity.getIdentityCode());
                        ownerEntity1.setAuthenticationTime(ownerEntity.getAuthenticationTime());
                        ownerEntity1.setUrgentName(ownerEntity.getUrgentName());
                        ownerEntity1.setUrgentPhone(ownerEntity.getUrgentPhone());
                        baseDao.save(ownerEntity1);
                    }
                    else
                    {
                        baseDao.save(ownerEntity);
                    }
                    String propertyCodeList = map.get(BindHead.PROPERTYCODELIST);
                    for (String propertyCode : Arrays.asList(propertyCodeList.split(";")))
                    {
                        PropertyEntity propertyEntity = propertyDao.getByCode(propertyCode);
                        PropertyOwnerInfoEntity propertyOwnerInfoEntity = new PropertyOwnerInfoEntity();
                        propertyOwnerInfoEntity.setStatus(BindStatus.CHECKED);
                        propertyOwnerInfoEntity.setUserRole(UserType.OWNER);
                        propertyOwnerInfoEntity.setPhone(ownerEntity.getPhone());
                        propertyOwnerInfoEntity.setPropertyId(propertyEntity.getId());
                        propertyOwnerInfoEntity.setBuildingId(propertyEntity.getBuildingId());
                        baseDao.save(propertyOwnerInfoEntity);
                    }
                    succNum+=1;
                }
                catch (Exception e)
                {
                    errorNum += 1;
                    errorDescription.add("写入失败: <br/>"+e.getMessage() + gson.toJson(map));
                }
            }

        }

        excelImportReport.setErrorNum(errorNum);
        excelImportReport.setErrorDescription(errorDescription);
        excelImportReport.setSuccNum(succNum);
        return excelImportReport;
    }

    @Override
    public ExcelImportReport importSecret(List<Map<String, String>> result)
    {
        ExcelImportReport excelImportReport=new ExcelImportReport();
        Integer errorNum=0;
        Integer succNum=0;
        boolean check;
        Gson gson=new Gson();
        List<String> errorDescription=new ArrayList<>();
        for (Map<String, String> map : result)
        {
            check=true;
            SsidSecretEntity ssidSecretEntity=new SsidSecretEntity();

            //设置锁的编码
            if (check)
            {
                String code=map.get(SecretHead.CODE);
                if (code.equals(""))
                {
                    errorNum+=1;
                    errorDescription.add("锁的编号不能为空: "+gson.toJson(map));
                    check=false;
                }
                else
                    ssidSecretEntity.setSymbol(code);
            }

            //设置锁的控制对象类型
            if (check)
            {
                Byte controlType= Byte.valueOf(map.get(SecretHead.CONTROLTYPE));
                ssidSecretEntity.setControlType(controlType);
            }

            //设置锁的控制对象id

            //设置

        }
        return excelImportReport;
    }
}
