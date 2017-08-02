package com.allen.dao.fee.studentfee;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/7/17.
 */
@Service
public class FindStudentFeeDao extends BaseQueryDao {

    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        /**
         * 分页查询信息
         * @param pageInfo
         * @param paramsMap
         * @param sortMap
         * @return
         * @throws Exception
         */
        String fileds = "s.id, s.code, s.name, s.sex, sc.name scName, rt.name rtName, l.name lName, sp.name spName, tp.year, tp.term, u.name uName, s.fee_state";
        String[] tableNames = {"student s", "fee_type ft", "teach_plan tp", "school sc", "recruit_type rt", "level l", "spec sp", "user u"};
        String defaultWhere = "s.school_id = ft.school_id and s.recruit_type_id = ft.type_id and s.level_id = ft.level_id and ft.school_id = tp.school_id and ft.type_id = tp.type_id and ft.level_id = tp.level_id " +
                "and ft.year = tp.year and ft.term = tp.term and s.school_id = sc.id and s.recruit_type_id = rt.id and s.level_id = l.id and s.spec_id = sp.id and s.user_id = u.id";
        String groupBy = "id, code, name, sex, scName, rtName, lName, spName, year, term, uName, fee_state";
        return super.findPageByNativeSqlToMap(pageInfo, fileds, defaultWhere, tableNames, groupBy, paramsMap, sortMap);
    }
}
