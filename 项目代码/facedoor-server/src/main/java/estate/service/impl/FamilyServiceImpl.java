package estate.service.impl;

import estate.common.util.Convert;
import estate.dao.FamilyDao;
import estate.entity.database.FamilyEntity;
import estate.entity.display.Family;
import estate.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("familyService")
public class FamilyServiceImpl implements FamilyService
{
    @Autowired
    private FamilyDao familyDao;


    public Set<Family> getFamiliesByOwnerID(Integer id)
    {
        ArrayList<FamilyEntity> entities=familyDao.getFamilyEntitiesByOwnerId(id);
        if (entities==null)
            return null;
        Set<Family> families=new HashSet<Family>();
        for (FamilyEntity familyEntity:entities)
        {
            Family family=new Family();
            family.setName(familyEntity.getName());
            family.setIdentityCode(familyEntity.getIdentityCode());
            family.setOwnerRelationship(familyEntity.getOwnerRelationship());
            family.setPhone(familyEntity.getPhone());

            family.setIdentityType(Convert.num2idtype(familyEntity.getIdentityType()));
            family.setBirthday(Convert.num2time(familyEntity.getBirthday()));
            family.setSex(Convert.num2sex(familyEntity.getSex()));

            families.add(family);
        }
        return families;
    }
}
