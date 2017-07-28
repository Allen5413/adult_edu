package com.allen.dao.eduadmin.student;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/7/6.
 */
@Service
public class FindStudentDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fields = "s.id, s.name, s.sex, s.code, sc.name scName, rt.name rtName, l.name lName, sp.name spName, s.state, tp.year, tp.term";
        String[] tableNames = {"student s, school sc, recruit_type rt, level l, spec sp, teach_plan tp, user u"};
        String defaultWhere = "s.school_id = sc.id and s.recruit_type_id = rt.id and s.level_id = l.id and s.spec_id = sp.id and s.teach_plan_id = tp.id and s.user_id = u.id";
        return super.findPageByNativeSqlToMap(pageInfo, fields, defaultWhere, tableNames,paramsMap, sortMap);
    }
}
