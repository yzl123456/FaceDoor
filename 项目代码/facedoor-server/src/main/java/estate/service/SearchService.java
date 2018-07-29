package estate.service;

/**
 * Created by 应泽林 on 18-2-25.
 * 搜索服务
 */
public interface SearchService
{
    /**
     * 通过名称搜索园区
     * @param name
     * @return
     */
    Object villageByName(String name);

    /**
     * 通过业主电话搜索业主姓名
     * @param phone
     * @return
     */
    Object ownerSearch(String phone);
}
