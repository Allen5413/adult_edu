package com.allen.dao.fee.studentfee;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        String groupBy = "s.id, s.code, s.name, s.sex, sc.name, rt.name, l.name, sp.name, tp.year, tp.term, u.name, s.fee_state";
        return super.findPageByNativeSqlToMap(pageInfo, fileds, defaultWhere, tableNames, groupBy, paramsMap, sortMap);
    }


    /**
     * 查询一个学生的缴费详情
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<Map> findByStudentId(Long studentId)throws Exception{
        Map<String, Object> paramsMap = new HashMap<String, Object>(1);
        paramsMap.put("sf.student_id", studentId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>(2);
        sortMap.put("sf.operate_time", false);
        sortMap.put("ft.id", true);
        String fileds = "SUBSTR(sf.operate_time,1,10) feeDate, sf.id, ft.name, sf.fee_style, ROUND(sf.fee/100, 2) fee, ROUND(sf.fxs_fee/100, 2) fxsFee, sf.operate_time, ROUND(ft.fee/100,2) typeFee";
        String[] tableNames = {"student_fee sf", "fee_type ft"};
        String defaultWhere = "sf.fee_type_id = ft.id";
        return super.findListBySqlToMap(tableNames, fileds, defaultWhere, paramsMap, sortMap);
    }


    /**
     * 查询一个招生类型下的相同批次年季的应缴费人数，已缴清人数，未缴完人数和未缴费人数
     * @return
     * @throws Exception
     */
    public List<Map> countFeeNumByRtIdAndYearAndTerm(long rtId, int year, int term)throws Exception{
        Map<String, Object> paramsMap = new HashMap<String, Object>(1);
        paramsMap.put("s.recruit_type_id", rtId);
        paramsMap.put("tp.year", year);
        paramsMap.put("tp.term", term);
        String fileds = "count(*) feeNum, ifnull((case when s.fee_state = 0 then count(*) end), 0) notPayFeeNum, ifnull((case when s.fee_state = 1 then count(*) end), 0) notCleanNum, ifnull((case when s.fee_state = 2 then count(*) end), 0) payFeeNum";
        String[] tableNames = {"student s", "teach_plan tp"};
        String defaultWhere = "s.teach_plan_id = tp.id";
        return super.findListBySqlToMap(tableNames, fileds, defaultWhere, paramsMap, null);
    }
}
