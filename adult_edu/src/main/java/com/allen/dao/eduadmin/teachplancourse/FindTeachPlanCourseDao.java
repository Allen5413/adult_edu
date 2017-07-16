package com.allen.dao.eduadmin.teachplancourse;

import com.allen.dao.BaseQueryDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/16 0016.
 */
@Service
public class FindTeachPlanCourseDao extends BaseQueryDao {

    public List<Map> findByTeachPlanId(Map<String, Object> paramMap, Map<String, Boolean> sortMap)throws Exception{
        String fileds = "tpc.*, c.code, c.name";
        String[] tables = {"teach_plan_course tpc", "course c"};
        String defaultWhere = "tpc.course_id = c.id";
        return super.findListBySqlToMap(tables, fileds, defaultWhere, paramMap, sortMap);
    }
}
