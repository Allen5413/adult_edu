package com.allen.dao.basic.level;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/7/6.
 */
@Service
public class FindLevelDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String[] tableNames = {"Level"};
        return super.findPageByJpal(pageInfo, tableNames, paramsMap, sortMap);
    }
}
