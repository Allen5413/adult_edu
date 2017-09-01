package com.allen.dao.basic.school;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/5.
 */
@Service
public class FindSchoolDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fileds = "s.id, s.code, s.name, s.link_man linkMan, s.phone, u.name uName";
        String[] tableNames = {"School s, User u"};
        String defaultWhere = "s.user_id = u.id";
        return super.findPageByNativeSqlToMap(pageInfo, fileds, defaultWhere, tableNames, paramsMap, sortMap);
    }

    /**
     * 查询一个学习中心下面的高校和专业集合
     * @param centerId
     * @return
     * @throws Exception
     */
    public List<Map> findByCenterIdForSchoolAndSpec(long centerId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("sc.center_id", centerId);
        Map<String, Boolean> sortMaps = new HashMap<String, Boolean>();
        sortMaps.put("sc.id", true);
        sortMaps.put("sp.id", true);
        String fields = "sc.id scId, sc.name scName, sp.id spId, sp.name spName";
        String[] tableNames = {"school sc", "spec sp"};
        String defaultWhere = "sc.id = sp.school_id";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, sortMaps);
    }
}
