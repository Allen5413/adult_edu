package com.allen.dao.fee.feetype;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

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
}
