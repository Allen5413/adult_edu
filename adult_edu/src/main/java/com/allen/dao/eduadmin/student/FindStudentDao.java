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
        String fields = "s.id, s.name, s.sex, s.code, sc.name scName, rt.name rtName, l.name lName, sp.name spName, s.state, tp.year, tp.term, s.phone, s.photo_url photoUrl, u.name uName, s.sign_up_date signUpDate, u2.name scuName, s.fee_state feeState";
        String[] tableNames = {"student s, school sc, recruit_type rt, level l, spec sp, teach_plan tp, user u, user u2"};
        String defaultWhere = "s.school_id = sc.id and s.recruit_type_id = rt.id and s.level_id = l.id and s.spec_id = sp.id and s.teach_plan_id = tp.id and s.user_id = u.id and sc.user_id = u2.id";
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

    /**
     * 统计一个招生类型下的各个学习状态的学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countNumForStateByRtId(long rtId, Long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("s.recruit_type_id", rtId);
        paramMaps.put("s.user_id", userId);
        String fields = "ifnull((case when s.state = 0 then count(*) end), 0) zdNum," +
                "ifnull((case when s.state = 1 then count(*) end), 0) xxNum," +
                "ifnull((case when s.state = 2 then count(*) end), 0) txNum," +
                "ifnull((case when s.state = 3 then count(*) end), 0) byNum";
        String[] tableNames = {"student s"};
        String defaultWhere = "1=1";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的各个层次学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countNumForLevelByRtId(long rtId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        String fields = "l.name, count(*) num";
        String[] tableNames = {"student su", "level l"};
        String defaultWhere = "su.level_id = l.id";
        String groupBy = "l.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的各个层次学生人数（分销商）
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countNumForLevelByRtIdAndUserId(long rtId, long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("su.user_id", userId);
        String fields = "l.name, count(*) num";
        String[] tableNames = {"student su", "level l"};
        String defaultWhere = "su.level_id = l.id";
        String groupBy = "l.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的各个批次学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countNumForTPByRtId(long rtId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        String fields = "tp.year, tp.term, count(*) num";
        String[] tableNames = {"student su", "teach_plan tp"};
        String defaultWhere = "su.teach_plan_id = tp.id";
        String groupBy = "tp.year, tp.term";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的各个批次学生人数（分销商）
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countNumForTPByRtIdAndUserId(long rtId, long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("su.user_id", userId);
        String fields = "tp.year, tp.term, count(*) num";
        String[] tableNames = {"student su", "teach_plan tp"};
        String defaultWhere = "su.teach_plan_id = tp.id";
        String groupBy = "tp.year, tp.term";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countByRtIdAndYearAndTerm(long rtId, int year, int term)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("tp.year", year);
        paramMaps.put("tp.term", term);
        String fields = "count(*) totalNum, ifnull((CASE WHEN su.user_id = -1 THEN count(*) END), 0) centerNum, ifnull((CASE WHEN su.user_id > -1 THEN count(*) END), 0) fxsNum ";
        String[] tableNames = {"student su", "teach_plan tp"};
        String defaultWhere = "su.teach_plan_id = tp.id";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的各个分销商学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countForFXSByRtIdAndYearAndTerm(long rtId, int year, int term)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("tp.year", year);
        paramMaps.put("tp.term", term);
        String fields = "u.id, u.name, count(*) num";
        String[] tableNames = {"student su", "user u", "teach_plan tp"};
        String defaultWhere = "su.user_id = u.id and su.user_id > -1 and su.teach_plan_id = tp.id";
        String groupBy = "u.id, u.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的各个层次学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countForLevelByRtIdAndYearAndTerm(long rtId, int year, int term, Long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("tp.year", year);
        paramMaps.put("tp.term", term);
        paramMaps.put("su.user_id", userId);
        String fields = "l.name, count(*) num";
        String[] tableNames = {"student su", "level l", "teach_plan tp"};
        String defaultWhere = "su.level_id = l.id and su.teach_plan_id = tp.id";
        String groupBy = "l.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的各个高校学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countForSchoolByRtIdAndYearAndTerm(long rtId, int year, int term, Long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("tp.year", year);
        paramMaps.put("tp.term", term);
        paramMaps.put("su.user_id", userId);
        String fields = "sc.name, count(*) num";
        String[] tableNames = {"student su", "school sc", "teach_plan tp"};
        String defaultWhere = "su.school_id = sc.id and su.teach_plan_id = tp.id";
        String groupBy = "sc.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的各个专业学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countForSpecByRtIdAndYearAndTerm(long rtId, int year, int term, Long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("tp.year", year);
        paramMaps.put("tp.term", term);
        paramMaps.put("su.user_id", userId);
        String fields = "sp.name, count(*) num";
        String[] tableNames = {"student su", "spec sp", "teach_plan tp"};
        String defaultWhere = "su.spec_id = sp.id and su.teach_plan_id = tp.id";
        String groupBy = "sp.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的各个批次的在读学生人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countTPNumForStateNORMALByRtId(Long rtId, Long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("s.recruit_type_id", rtId);
        paramMaps.put("s.user_id", userId);
        Map<String, Boolean> sortMaps = new HashMap<String, Boolean>();
        sortMaps.put("tp.year", false);
        sortMaps.put("tp.term", false);
        String fields = "tp.year, tp.term, ifnull((case when s.state = 0 then count(*) end), 0) num";
        String[] tableNames = {"student s", "teach_plan tp"};
        String defaultWhere = "s.teach_plan_id = tp.id";
        String groupBy = "tp.year, tp.term";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, sortMaps);
    }


    /**
     * 统计一个中心下的各种状态的学生数量,包括总人数、在读人数、休学人数、毕业人数、未缴费人数、未缴完人数
     * @param centerId
     * @return
     * @throws Exception
     */
    public List<Map> countNumForStateWhereByCenterIdAndUserId(Long centerId, Long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("s.center_id", centerId);
        if(null != userId && -2 == userId){
            paramMaps.put("s.user_id", new Object[]{-1, "!="});
        }else {
            paramMaps.put("s.user_id", userId);
        }
        String fields = "count(*) totalNum, " +
                "ifnull(sum(CASE state WHEN 0 THEN 1 ELSE 0 END),0) zdNum, " +
                "ifnull(sum(CASE state WHEN 1 THEN 1 ELSE 0 END),0) xxNum, " +
                "ifnull(sum(CASE state WHEN 3 THEN 1 ELSE 0 END),0) byNum, " +
                "ifnull(sum(CASE fee_state WHEN 0 THEN 1 ELSE 0 END),0) notPayNum, " +
                "ifnull(sum(CASE fee_state WHEN 1 THEN 1 ELSE 0 END),0) notCleanNum";
        String[] tableNames = {"student s"};
        String defaultWhere = "1=1";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, null);
    }

    /**
     * 统计各个批次的在各种状态的学生人数
     * 包括，总人数，在读人数，休学人数，退学人数，毕业人数，未缴完学费人数
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Map> countTPNumForStateWhereByUserId(Long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("s.user_id", userId);
        Map<String, Boolean> sortMaps = new HashMap<String, Boolean>();
        sortMaps.put("tp.year", false);
        sortMaps.put("tp.term", false);
        String fields = "tp.year,tp.term,count(*) zsNum," +
                "sum(CASE state WHEN 0 THEN 1 ELSE 0 END) zdNum," +
                "sum(CASE state WHEN 1 THEN 1 ELSE 0 END) xxNum," +
                "sum(CASE state WHEN 2 THEN 1 ELSE 0 END) txNum," +
                "sum(CASE state WHEN 3 THEN 1 ELSE 0 END) byNum," +
                "sum(CASE fee_state WHEN 1 THEN 1 ELSE 0 END) notCleanNum";
        String[] tableNames = {"student s", "teach_plan tp"};
        String defaultWhere = "s.teach_plan_id = tp.id";
        String groupBy = "tp.year, tp.term";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, sortMaps);
    }
}
