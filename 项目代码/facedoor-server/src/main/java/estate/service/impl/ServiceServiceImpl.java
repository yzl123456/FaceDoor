package estate.service.impl;

import estate.entity.json.TableData;
import estate.entity.json.TableFilter;
import estate.service.ServiceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("service")
public class ServiceServiceImpl implements ServiceService
{
    public ArrayList<TableData> getRepairList(TableFilter tableFilter)
    {
        return null;
    }
}
