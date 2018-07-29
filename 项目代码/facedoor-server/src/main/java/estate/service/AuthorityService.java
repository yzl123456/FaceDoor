package estate.service;

import java.util.ArrayList;
/**
 * Created by 应泽林 on 18-1-22.
 */
public interface AuthorityService
{
    /**
     * 根据业主的电话返回该业主名下所有有权限进入的楼栋id
     * @param phone
     * @return
     */
    ArrayList<Integer> getAuthorityBuildingIDsByPhone(String phone);
}
