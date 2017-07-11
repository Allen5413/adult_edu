package com.allen.dao.basic.course;

import com.allen.dao.BaseQueryDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/11.
 */
@Service
public class FindCourseDao extends BaseQueryDao {
    /**
     * 查询一个高校的招生类型下的一个层次的所有专业
     * @param stlsId
     * @return
     * @throws Exception
     */
    public List<Map> findBySTLSId(long stlsId)throws Exception{
        String fileds = "stlsc.id, c.id cId, c.code, c.name";
        String[] tables = {"school_type_level_spec_course stlsc", "course c"};
        String defaultWhere = "stlsc.course_id = c.id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("stlsc.school_type_level_spec_id", stlsId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("c.code", true);
        return super.findListBySqlToMap(tables, fileds, defaultWhere, paramMap, sortMap);
    }
}
