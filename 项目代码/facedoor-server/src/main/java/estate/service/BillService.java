package estate.service;

import estate.entity.database.BillEntity;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.exception.PropertyNotBindFeeItemException;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface BillService
{

    /**
     * 获取datatable显示的数据
     * @param tableFilter
     * @return
     */
    TableData getList(TableFilter tableFilter);

    /**
     * 通过物业的id获取该物业对应的账单
     * @param id
     * @return
     */
    ArrayList<BillEntity> getBillByPropertyID(Integer id);

    /**
     * 通过app用户的电话获取账单信息
     * @param phone
     * @return
     */
    ArrayList<BillEntity> getBillByAppUserPhone(String phone,Byte status);

    /**
     * 根据物业id生成这个物业的账单
     * @param id
     */
    void generateBillByPropertyID(Integer id) throws PropertyNotBindFeeItemException;

    /**
     * 通过用户的电话获取这个用户的车位费账单
     * @param phone
     * @return
     */
    Object getParkLotBillByPhone(String phone);
}
