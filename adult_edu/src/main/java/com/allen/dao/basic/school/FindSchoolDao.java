package com.allen.dao.basic.school;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/7/5.
 */
@Service
public class FindSchoolDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String[] tableNames = {"School s"};
        return super.findPageByJpal(pageInfo, tableNames, paramsMap, sortMap);
    }
}
