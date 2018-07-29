package estate.service.impl;

import estate.dao.SearchDao;
import estate.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService
{
    @Autowired
    private SearchDao searchDao;

    @Override
    public Object villageByName(String name)
    {
        return searchDao.villageSearch(name);
    }

    @Override
    public Object ownerSearch(String phone)
    {
        return searchDao.ownerSearch(phone);
    }
}
