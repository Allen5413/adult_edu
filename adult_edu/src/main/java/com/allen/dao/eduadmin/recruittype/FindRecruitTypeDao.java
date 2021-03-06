package com.allen.dao.eduadmin.recruittype;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Service
public class FindRecruitTypeDao extends BaseQueryDao{

    /**
     * 分页查询信息
     * @param pageInfo
     * @param paramsMap
     * @param sortMap
     * @return
     * @throws Exception
     */
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String[] tableNames = {"RecruitType"};
        return super.findPageByJpal(pageInfo, tableNames, paramsMap, sortMap);
    }
}
