# FaceDoor智慧人脸门禁系统项目

**项目展示**  
业主APP端： 

![image](https://github.com/yzl123456/FaceDoor/blob/master/项目展示/业主APP端/2-3_主页.png?raw=true)  
![image](https://github.com/yzl123456/FaceDoor/blob/master/项目展示/业主APP端/6_亲友访客.png?raw=true)

人脸注册机端：  

![image](https://github.com/yzl123456/FaceDoor/blob/master/项目展示/人脸注册机端/首页.png?raw=true)  
![image](https://github.com/yzl123456/FaceDoor/blob/master/项目展示/人脸注册机端/人脸更新.png?raw=true)  

B端：  

![image](https://github.com/yzl123456/FaceDoor/blob/master/项目展示/服务器端/门禁流量统计.png?raw=true)  
![image](https://github.com/yzl123456/FaceDoor/blob/master/项目展示/服务器端/人脸识别时间统计.png?raw=true)  
![image](https://github.com/yzl123456/FaceDoor/blob/master/项目展示/服务器端/门禁访问分级.png?raw=true)  
![image](https://github.com/yzl123456/FaceDoor/blob/master/项目展示/服务器端/门禁进出记录.png?raw=true)  

门禁客户端：



**文件说明**  

该项目主要分为服务器端、人脸注册机端、门禁客户端、业主APP端共4个端组成。  

项目代码都放在了“项目代码”文件夹下，其中“FaceDoorClient_RegisterMachine”表示门禁客户端和人脸注册机端，“FaceDoor”表示业主APP端，“facedoor-server”表示服务器端。  

apk安装包为我已经导出的apk文件，可以先自行把apk导入手机进行体验。  

项目演示文件夹下为各个端的部分演示，更多的项目演示可去该文件夹下查看。  


**开发环境说明**  

服务器端：
* win 10
* jdk 1.8
* eclipse

人脸注册机端：
* win 10
* jdk 1.8
* android studio

门禁客户端：
* win 10
* jdk 1.8
* android studio

业主APP端：
* win 10
* jdk 1.8
* android studio



**项目概述**  

服务器端：  

服务器采用了Spring MVC整合Hibernate框架进行搭建，对应的Controller处理对应的请求进行应答，B端采用了Bootstrap、JQuery等前端框架，可视化图表是采用highcharts框架开发的。  

人脸注册机端：  

在android studio中开发完成，用户拍照进行人脸注册，将人脸信息（人脸特征点）保存到数据库中。  

门禁客户端：  

在android stduio中开发完成，集成了虹软人脸识别的SDK，将摄像头此刻对应的人脸特征与数据库中已存在的人脸特征进行比较，从而找出最匹配的那个人。  

业主APP端：  

APP端的功能主要分为四大模块，分别为门禁、社区、商城以及个人中心的管理。业主可以在app上方便有效的管理家人、亲友以及访客的门禁进出权限，也可在app上进行生活日常用品的购买等。  





