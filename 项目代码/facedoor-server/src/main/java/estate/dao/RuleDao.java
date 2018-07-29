package estate.dao;

import estate.entity.database.RuleEntity;

/**
 * Created by 应泽林 on 18-1-15.
 *
 */
public interface RuleDao
{
    /**
     * 保存或者更新规则并且返回规则ID
     * @param ruleEntity 规则实体
     * @return 返回保存后的规则ID
     */
    Integer save(RuleEntity ruleEntity);

    /**
     *根据规则ID删除相应的规则
     * @param ruleID
     */
    void delete(Integer ruleID);

    /**
     * 根据规则ID获取相应的规则
     * @param ruleID
     * @return
     */
    RuleEntity get (Integer ruleID);



}
