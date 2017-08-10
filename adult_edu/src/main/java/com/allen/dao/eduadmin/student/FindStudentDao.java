package com.allen.dao.eduadmin.student;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    /**
     * 统计一个招生类型下没有缴费的人数和学费未交完的人数和已交完的人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countFeeStateNumByRtId(long rtId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("s.recruit_type_id", rtId);
        String fields = "ifnull((case when s.fee_state = 0 then count(*) end), 0) notFeeNum,ifnull((case when s.fee_state = 1 then count(*) end),0) notCleanNum,ifnull((case when s.fee_state = 2 then count(*) end),0) overNum";
        String[] tableNames = {"student s"};
        String defaultWhere = "1=1";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, null);
    }
}
