package estate.service.impl;

import estate.dao.ApartmentDao;
import estate.dao.BaseDao;
import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("apartmentService")
public class ApartmentServiceImpl implements ApartmentService
{
    @Autowired
    private ApartmentDao apartmentDao;
    @Autowired
    private BaseDao baseDao;

    @Override
    public TableData getList(TableFilter tableFilter)
    {
        TableData tableData=apartmentDao.getList(tableFilter);
        tableData.setRecordsTotal(baseDao.count("ApartmentEntity"));
        return tableData;
    }
}
