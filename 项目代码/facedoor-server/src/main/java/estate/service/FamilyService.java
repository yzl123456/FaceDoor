package estate.service;

import estate.entity.display.Family;

import java.util.Set;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface FamilyService
{
    /**
     * 通过业主的ID获取所有的家庭成员
     * @param id
     * @return
     */
    Set<Family> getFamiliesByOwnerID(Integer id);

}
