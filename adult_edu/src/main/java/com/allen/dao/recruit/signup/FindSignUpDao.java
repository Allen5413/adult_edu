package com.allen.dao.recruit.signup;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/6.
 */
@Service
public class FindSignUpDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String[] tableNames = {"SignUp su"};
        return super.findPageByJpal(pageInfo, tableNames, paramsMap, sortMap);
    }

    /**
     * 统计一个招生类型下的各个层次报名人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countForLevelByRtId(long rtId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        String fields = "l.name, count(*) num";
        String[] tableNames = {"sign_up su", "level l"};
        String defaultWhere = "su.level_id = l.id";
        String groupBy = "l.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的各个层次报名人数（分销商）
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countForLevelByRtIdAndUserId(long rtId, long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("su.user_id", userId);
        String fields = "l.name, count(*) num";
        String[] tableNames = {"sign_up su", "level l"};
        String defaultWhere = "su.level_id = l.id";
        String groupBy = "l.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的各个批次报名人数
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countForTPByRtId(long rtId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        String fields = "tp.year, tp.term, count(*) num";
        String[] tableNames = {"sign_up su", "teach_plan tp"};
        String defaultWhere = "su.teach_plan_id = tp.id";
        String groupBy = "tp.year, tp.term";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的各个批次报名人数（分销商）
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countForTPByRtIdAndUserId(long rtId, long userId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.recruit_type_id", rtId);
        paramMaps.put("su.user_id", userId);
        String fields = "tp.year, tp.term, count(*) num";
        String[] tableNames = {"sign_up su", "teach_plan tp"};
        String defaultWhere = "su.teach_plan_id = tp.id";
        String groupBy = "tp.year, tp.term";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的报名人数
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
        String[] tableNames = {"sign_up su", "teach_plan tp"};
        String defaultWhere = "su.teach_plan_id = tp.id";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的各个分销商报名人数
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
        String[] tableNames = {"sign_up su", "user u", "teach_plan tp"};
        String defaultWhere = "su.user_id = u.id and su.user_id > -1 and su.teach_plan_id = tp.id";
        String groupBy = "u.id, u.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的各个层次报名人数
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
        String[] tableNames = {"sign_up su", "level l", "teach_plan tp"};
        String defaultWhere = "su.level_id = l.id and su.teach_plan_id = tp.id";
        String groupBy = "l.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的各个高校报名人数
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
        String[] tableNames = {"sign_up su", "school sc", "teach_plan tp"};
        String defaultWhere = "su.school_id = sc.id and su.teach_plan_id = tp.id";
        String groupBy = "sc.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }

    /**
     * 统计一个招生类型下的一个批次下的各个专业报名人数
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
        String[] tableNames = {"sign_up su", "spec sp", "teach_plan tp"};
        String defaultWhere = "su.spec_id = sp.id and su.teach_plan_id = tp.id";
        String groupBy = "sp.name";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, null);
    }


    /**
     * 查询一个手机号码下的报名信息
     * @param phone
     * @return
     * @throws Exception
     */
    public List<Map> findByPhone(String phone)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("su.phone", phone);
        String fields = "su.id, sc.logo, sc.name, sp.name specName";
        String[] tableNames = {"sign_up su", "school sc", "spec sp"};
        String defaultWhere = "su.school_id = sc.id and su.spec_id = sp.id";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, null);
    }
}
