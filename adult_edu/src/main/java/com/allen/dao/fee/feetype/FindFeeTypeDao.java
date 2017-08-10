package com.allen.dao.fee.feetype;

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
public class FindFeeTypeDao extends BaseQueryDao {

    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        /**
         * 分页查询信息
         * @param pageInfo
         * @param paramsMap
         * @param sortMap
         * @return
         * @throws Exception
         */
        String fileds = "ft.*, ROUND(fee/100,2) money, CONCAT(year,'年',case when ft.term = 0 then '春季' ELSE '秋季' END) batch, s.name sName, rt.`name` rtName, l.`name` lName";
        String[] tableNames = {"fee_type ft", "school s", "recruit_type rt", "level l"};
        String defaultWhere = "ft.school_id = s.id and ft.type_id = rt.id and ft.level_id = l.id";
        return super.findPageByNativeSqlToMap(pageInfo, fileds, defaultWhere, tableNames, paramsMap, sortMap);
    }


    /**
     * 统计一个招生类型下相同入学年入学季的批次应交的费用
     * @param rtId
     * @return
     * @throws Exception
     */
    public List<Map> countTotalFeeForYearAndTermByRtId(long rtId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("s.recruit_type_id", rtId);
        Map<String, Boolean> sortMaps = new HashMap<String, Boolean>();
        sortMaps.put("tp.year", false);
        sortMaps.put("tp.term", false);
        String fields = "tp.year, tp.term, round(sum(ft.fee)/100,2) fee";
        String[] tableNames = {"student s", "teach_plan tp", "fee_type ft"};
        String defaultWhere = "s.teach_plan_id = tp.id and ft.type_id = s.recruit_type_id and ft.school_id = s.school_id and ft.level_id = s.level_id and ft.year = tp.year and ft.term = tp.term";
        String groupBy = "tp.year, tp.term";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramMaps, sortMaps);
    }
}
