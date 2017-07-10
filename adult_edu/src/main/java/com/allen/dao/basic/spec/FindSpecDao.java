package com.allen.dao.basic.spec;

import com.allen.dao.BaseQueryDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class FindSpecDao extends BaseQueryDao {

    /**
     * 查询一个高校的招生类型下的一个层次的所有专业
     * @param schoolId
     * @param typeId
     * @param levelId
     * @return
     * @throws Exception
     */
    public List<Map> findBySchoolIdAndTypeIdAndLevelId(long schoolId, long typeId, long levelId)throws Exception{
        String fileds = "stls.id, s.id sId, s.code, s.name";
        String[] tables = {"school_type_level_spec stls", "spec s"};
        String defaultWhere = "stls.spec_id = s.id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("stls.school_id", schoolId);
        paramMap.put("stls.recruit_type_id", typeId);
        paramMap.put("stls.level_id", levelId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.code", true);
        return super.findListBySqlToMap(tables, fileds, defaultWhere, paramMap, sortMap);
    }
}
