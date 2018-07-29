package estate.app;

import estate.common.util.Convert;
import estate.common.util.GsonUtil;
import estate.common.util.LogUtil;
import estate.entity.database.BillEntity;
import estate.entity.display.AppBill;
import estate.entity.json.BasicJson;
import estate.entity.json.Select2;
import estate.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-3-4.
 *
 */
@RestController
@RequestMapping(value = "/api/fee")
public class FeeHandler
{
    @Autowired
    private BillService billService;

    /**
     * 获取用户绑定的所有物业的张账单
     * @return
     */
    @RequestMapping(value = "/getBill")
    public BasicJson getBill(HttpServletRequest request)
    {
        BasicJson basicJson=new BasicJson();
        Byte billStatus=null;
        try
        {
            String status=request.getParameter("status");
            if (!(status.equals("0")||status.equals("1")))
            {
                basicJson.getErrorMsg().setDescription("账单状态参数错误");
                return basicJson;
            }
            billStatus = Byte.valueOf(status);
        }
        catch (Exception ignore){}

        HttpSession session=request.getSession();
        String phone= (String) session.getAttribute("phone");
        try
        {
            ArrayList<BillEntity> entities=billService.getBillByAppUserPhone(phone,billStatus);
            if (entities==null||entities.size()<1)
            {
                basicJson.setStatus(true);
                basicJson.getErrorMsg().setDescription("暂无账单");
                return basicJson;
            }
            ArrayList<AppBill> bills=new ArrayList<>();
            for (BillEntity billEntity:entities)
            {
                ArrayList<Select2> select2s=new ArrayList<>();
                float total=0;
                String feeInfo=billEntity.getFeeItemFee();
                String[] feeCostStrings=feeInfo.split(";");
                for (String feeCostString:feeCostStrings)
                {
                    String[] feeCostArray=feeCostString.split(":");
                    Select2 select2=new Select2();
                    select2.setId(feeCostArray[0]);
                    select2.setText(feeCostArray[1]);
                    select2s.add(select2);
                    total+=new Float(feeCostArray[1]);
                }

                AppBill appBill=new AppBill();
                appBill.setStatus(billEntity.getPayStatus());
                appBill.setItems(select2s);
                appBill.setId(billEntity.getId());
                appBill.setTotal(String.valueOf(total));
                appBill.setBillTime(Convert.num2time(billEntity.getBillGenerationTime(),"yyyy-MM"));
                bills.add(appBill);
            }
            LogUtil.E(GsonUtil.getGson().toJson(bills));
            ArrayList<Select2> parkLotBills= (ArrayList<Select2>) billService.getParkLotBillByPhone(phone);
            if (parkLotBills!=null)
            {
                float total=0;
                for (Select2 select2:parkLotBills)
                {
                    total+=new Float(select2.getText());
                }
                AppBill appBill = new AppBill();
                appBill.setItems(parkLotBills);
                appBill.setId(99);
                appBill.setTotal(String.valueOf(total));
                appBill.setStatus((byte) 0);
                appBill.setBillTime(Convert.num2time(System.currentTimeMillis()));
                bills.add(appBill);
            }
            basicJson.setJsonString(bills);
        }
        catch (Exception e)
        {
            basicJson.getErrorMsg().setDescription("获取账单失败!");
            LogUtil.E("dsdsdd"+e.getMessage());
            return basicJson;
        }
        basicJson.setStatus(true);
        return basicJson;
    }
}
