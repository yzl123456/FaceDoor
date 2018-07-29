package estate.dao;

import estate.entity.database.FamilyEntity;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-17.
 *
 */
public interface FamilyDao
{
    ArrayList<FamilyEntity> getFamilyEntitiesByOwnerId(Integer id);


    ArrayList<FamilyEntity> getFamilByPropertyID(Integer id);
}
