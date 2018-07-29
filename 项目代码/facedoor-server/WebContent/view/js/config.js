/**
 * Created by kangbiao on 15-9-14.
 * 该文件为web端接口全局配置文件
 */

/************--------------接口配置----------********************/
var host="/oa/web/";
var urlConfig={};

urlConfig.home="/oa/view/index.html";

//用户登陆
urlConfig.login=host+"auth/login";


urlConfig.setVillageIDCookie=host+"public/setcookie/";

//公告部分的url
urlConfig.noticeDelete=host+"notice/delete/";
urlConfig.noticeList=host+"notice/list";
urlConfig.addNotice=host+"notice/add";
urlConfig.pushNotice=host+"notice/pushNotice/";


//费用相关的URL
urlConfig.addParkLotFee=host+"fee/add/parkLot";
urlConfig.addFee=host+"fee/add/estate";
urlConfig.feeList=host+"fee/list/estate";
urlConfig.parkLotFeeList=host+"fee/list/parkLot";
urlConfig.deleteFee=host+"fee/delete/";
urlConfig.addServiceFee=host+"fee/add/service";
urlConfig.serviceFeeList=host+"fee/list/service";
urlConfig.addParkingLotFee=host+"fee/add/parkingLot";
urlConfig.parkingLotFeeList=host+"fee/list/parkingLot";
urlConfig.relateFeeToBuilding=host+"fee/relateBuilding";
urlConfig.getBillList=host+"fee/getBillList";
urlConfig.generateAllBill=host+"fee/getnerateAllBill";
urlConfig.pushBill=host+"fee/pushBill/";

//维修相关的URL
urlConfig.repairList=host+"repair/list";
urlConfig.setRepairMan=host+"repair/setRepairMan";
urlConfig.deleteRepair=host+"repair/delete/";
urlConfig.getPicturePathsByID=host+"repair/getPathsByID/";
urlConfig.finishRepair=host+"repair/finish/";
urlConfig.addRepairMan=host+"repair/addRepairMan";
urlConfig.getRepairManList=host+"repair/getRepairManList";

//投诉相关的URL
urlConfig.complainList=host+"complain/list";
urlConfig.deleteComplain=host+"complain/delete/";

//用户相关的URL
urlConfig.ownerList=host+"user/ownerList";
urlConfig.blackManList=host+"user/blackManList";
urlConfig.acceptVerify=host+"user/acceptVerify";
urlConfig.refuseVerify=host+"user/refuseVerify";
urlConfig.guestList=host+"user/guestList";
urlConfig.workerList=host+"user/workerList";
urlConfig.otherList=host+"user/otherList";
urlConfig.ownerVerifyList=host+"user/ownerVerifyList";
urlConfig.addOwner=host+"user/addOwner";
urlConfig.authenticatedUserList=host+"user/authenticatedUserList";
urlConfig.tenantList=host+"user/tenantList";
urlConfig.appUserList=host+"user/appUserList";
urlConfig.changeAppUserStatus=host+"user/appUserStatus";
urlConfig.getPropertyInfo=host+"user/getPropertyInfo";
urlConfig.getPropertiesByAppUserPhone=host+"user/appuser/getPropertiesByPhone/";
urlConfig.getPropertiesByOwnerPhone=host+"user/owner/getPropertiesByPhone/";
urlConfig.deleteOwnerByPhone=host+"/user/owner/delete/";
urlConfig.deleteGuestByID=host+"/user/guest/delete/";
urlConfig.getOwnerByID=host+"/user/owner/get/";
urlConfig.editOwnerByID=host+"/user/owner/edit/";
urlConfig.deleteAppUserByPhone=host+"/user/appuser/delete/";
urlConfig.getUserInfoByPhone=host+"/user/getUserInfoByPhone/";

//门禁相关的URL
urlConfig.doorList=host+"/door/doorList/";
urlConfig.changeDoorStatus=host+"/door/doorStatus"
urlConfig.doorManLimit=host+"/door/getManLimit"
urlConfig.changeManLimit=host+"/door/changeManLimit"
urlConfig.doorRecordList=host+"/door/doorRecordList/";

//密钥相关的url
urlConfig.addSecret=host+"secret/add";
urlConfig.secretList=host+"secret/list";

//物业信息相关的url
urlConfig.modifyProperty=host+"property/modify";
urlConfig.propertyList=host+"property/propertyList";
urlConfig.getOwnerInfoByPropertyID=host+"property/owner/";
urlConfig.getFeeInfoByPropertyID=host+"property/fee/";
urlConfig.addProperty=host+"property/add";
urlConfig.generateBill=host+"property/generateBill/";
urlConfig.getOwnerPropertyInfoByID=host+"property/getMoreInfo/";
urlConfig.deleteOwner=host+"property/deleteOwner";
urlConfig.deleteProperty=host+"property/deleteProperty/";
urlConfig.getBindInfo=host+"property/getBindInfo";

//园区相关的url
urlConfig.getVillageSelect2=host+"property/villageList";
urlConfig.villageList=host+"village/getList";
urlConfig.addVillage=host+"village/add";
urlConfig.deleteVillage=host+"village/delete/";

//楼栋相关的url
urlConfig.buildingList=host+"building/getList";
urlConfig.getBuildingSelect2=host+"property/buildingList/";
urlConfig.addBuilding=host+"building/add";
urlConfig.deleteBuilding=host+"building/delete/";

//道闸相关的url
urlConfig.brakeList=host+"brake/getList";
urlConfig.brakeDelete=host+"brake/delete/";
urlConfig.brakeAdd=host+"brake/add";
urlConfig.getBrakeListByVillageID=host+"brake/getByVillageID/";

//车位相关的url
urlConfig.parkLotAdd=host+"parkLot/add";
urlConfig.parkLotList=host+"parkLot/getList";
urlConfig.parkLotDelete=host+"parkLot/delete/";
urlConfig.parkLotOwner=host+"parkLot/getOwnerList/";
urlConfig.addParkLotOwner=host+"parkLot/addOwner";
urlConfig.deleteParkLotOwnerBind=host+"parkLot/deleteBind/";

//excel导入
urlConfig.propertyImport=host+"upload/excel/property";
urlConfig.bindImport=host+"upload/excel/bind";


//搜索
urlConfig.search=host+"search/";

//配置kindeditor路径
urlConfig.uploadJson=host+"upload/kindeditor";

/**********----------------语言配置----------------**********/
var langConvert=[];
langConvert['squre']="按平米";
langConvert["per"]="按次";
langConvert["family"]="按户";
langConvert["month"]="按月";
langConvert["day"]="按日";


