package estate.service.impl;

import estate.service.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 应泽林 on 18-1-22.
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService
{
    @Override
    public ArrayList<Integer> getAuthorityBuildingIDsByPhone(String phone)
    {
        ArrayList<Integer> integers=new ArrayList<>();
        return integers;
    }
}
