package com.allen.dao.eduadmin.studentcourse;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/4.
 */
@Service
public class FindStudentCourseDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, String> paramsMap)throws Exception{
        List<Object> paramsList = new ArrayList<Object>();
        String centerId = paramsMap.get("centerId");
        String name = paramsMap.get("name");
        String courseName = paramsMap.get("courseName");
        String score = paramsMap.get("score");
        String scId = paramsMap.get("schoolId");
        String studentId = paramsMap.get("studentId");
        String rtId = paramsMap.get("recruitTypeId");
        String lId = paramsMap.get("levelId");
        String spId = paramsMap.get("specId");
        String tpId = paramsMap.get("teachPlanId");

        String fileds = "s.id, s.name, s.code, sc.name scName, rt.name rtName, l.name lName, sp.name spName, c.name cName, scc.score, c.id cId, tp.year, tp.term";
        String sql = "from student s INNER JOIN school sc on s.school_id = sc.id " +
                "INNER JOIN recruit_type rt on s.recruit_type_id = rt.id " +
                "INNER JOIN level l on s.level_id = l.id " +
                "INNER JOIN spec sp on s.spec_id = sp.id " +
                "INNER JOIN teach_plan tp on s.teach_plan_id = tp.id " +
                "INNER JOIN teach_plan_course tpc on s.teach_plan_id = tpc.teach_plan_id " +
                "INNER JOIN course c on c.id = tpc.course_id " +
                "LEFT JOIN student_course scc on tpc.course_id = scc.course_id and s.id = scc.student_id " +
                "where s.center_id = ? ";
        paramsList.add(Long.parseLong(centerId));
        if(!StringUtil.isEmpty(name)){
            sql += "and s.name like ? ";
            paramsList.add("%"+name+"%");
        }
        if(!StringUtil.isEmpty(courseName)){
            sql += "and c.name like ? ";
            paramsList.add("%"+courseName+"%");
        }
        if(!StringUtil.isEmpty(score)){
            sql += "and scc.score = ? ";
            paramsList.add(Integer.parseInt(score));
        }
        if(!StringUtil.isEmpty(scId)){
            sql += "and s.school_id = ? ";
            paramsList.add(Long.parseLong(scId));
        }
        if(!StringUtil.isEmpty(rtId)){
            sql += "and s.recruit_type_id = ? ";
            paramsList.add(Long.parseLong(rtId));
        }
        if(!StringUtil.isEmpty(lId)){
            sql += "and s.level_id = ? ";
            paramsList.add(Long.parseLong(lId));
        }
        if(!StringUtil.isEmpty(spId)){
            sql += "and s.spec_id = ? ";
            paramsList.add(Long.parseLong(spId));
        }
        if(!StringUtil.isEmpty(tpId)){
            sql += "and s.teach_plan_id = ? ";
            paramsList.add(Long.parseLong(tpId));
        }
        if(!StringUtil.isEmpty(studentId)){
            sql += "and s.id = ? ";
            paramsList.add(Long.parseLong(studentId));
        }
        sql += "order by s.code, c.code";

        return super.pageSqlQueryByNativeSqlToMap(pageInfo, sql, fileds, paramsList.toArray());
    }
}
