package com.allen.dao.eduadmin.teachplan;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Service
public class FindTeachPlanDao extends BaseQueryDao{

    /**
     * 分页查询信息
     * @param pageInfo
     * @param paramsMap
     * @param sortMap
     * @return
     * @throws Exception
     */
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fiedls = "tp.*, s.name sName, rt.name rtName, l.name lName, sp.name spName";
        String[] tableNames = {"teach_plan tp", "school s", "recruit_type rt", "level l", "spec sp"};
        String defaultWhere = "tp.school_id = s.id and tp.type_id = rt.id and tp.level_id = l.id and tp.spec_id = sp.id";
        return super.findPageByNativeSqlToMap(pageInfo, fiedls, defaultWhere, tableNames, paramsMap, sortMap);
    }
}
