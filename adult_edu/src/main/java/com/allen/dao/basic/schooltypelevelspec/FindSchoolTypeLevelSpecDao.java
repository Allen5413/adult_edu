package com.allen.dao.basic.schooltypelevelspec;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class FindSchoolTypeLevelSpecDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, String> paramsMap)throws Exception{
        List<Object> paramsList = new ArrayList<Object>();
        String centerId = paramsMap.get("centerId");
        String schoolId = paramsMap.get("schoolId");
        String typeId = paramsMap.get("typeId");
        String levelId = paramsMap.get("levelId");

        String fileds = "s.id sId, s.name sName, rt.id rtId, rt.name rtName, l.id lId, l.name lName, count(*) count";
        String sql = "from school s, recruit_type rt, level l, school_type_level_spec stls ";
        sql += "where s.id = stls.school_id and rt.id = stls.recruit_type_id and l.id = stls.level_id and s.center_id = ? ";
        paramsList.add(Long.parseLong(centerId));
        if(!StringUtil.isEmpty(schoolId)){
            sql += "and s.id = ? ";
            paramsList.add(Long.parseLong(schoolId));
        }
        if(!StringUtil.isEmpty(typeId)){
            sql += "and rt.id = ? ";
            paramsList.add(Long.parseLong(typeId));
        }
        if(!StringUtil.isEmpty(levelId)){
            sql += "and l.id = ? ";
            paramsList.add(Long.parseLong(levelId));
        }
        sql += "group by s.id, s.name, rt.id, rt.name, l.id, l.name ";
        sql += "order by s.id, rt.id, l.id";
        return super.pageSqlQueryByNativeSqlToMap(pageInfo, sql, fileds, paramsList.toArray());
    }
}
