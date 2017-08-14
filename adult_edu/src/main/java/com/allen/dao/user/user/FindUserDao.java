package com.allen.dao.user.user;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/4.
 */
@Service
public class FindUserDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, String> paramsMap, String orderBy)throws Exception{
        List<Object> paramsList = new ArrayList<Object>();
        String centerId = paramsMap.get("centerId");
        String type = paramsMap.get("type");
        String state = paramsMap.get("state");
        String name = paramsMap.get("name");

        String fileds = "u.*, ug.`name` ugName";
        String sql = "from user u LEFT JOIN user_group_user ugu on u.id = ugu.user_id ";
        sql += "LEFT JOIN user_group ug on ugu.user_group_id = ug.id ";
        sql += "where u.state > 0 and u.center_id = ? ";
        paramsList.add(Long.parseLong(centerId));
        if(!StringUtil.isEmpty(type)){
            sql += "and u.type = ? ";
            paramsList.add(Integer.parseInt(type));
        }
        if(!StringUtil.isEmpty(state)){
            sql += "and u.state = ? ";
            paramsList.add(Integer.parseInt(state));
        }
        if(!StringUtil.isEmpty(name)){
            sql += "and u.name like ? ";
            paramsList.add("%"+name+"%");
        }
        sql += "order by "+orderBy;

        return super.pageSqlQueryByNativeSqlToMap(pageInfo, sql, fileds, paramsList.toArray());
    }


    /**
     * 查询一个学习中心下的通讯录，只要就是中心子账户和管理员
     * @param centerId
     * @return
     * @throws Exception
     */
    public List<Map> findByCenterIdForCenterMan(Long centerId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("u.center_id", centerId);
        Map<String, Boolean> sortMaps = new HashMap<String, Boolean>();
        sortMaps.put("u.type", true);
        String fields = "u.id, u.name, u.phone, ug.name ugName";
        String[] tableNames = {"user u", "user_group_user ugu", "user_group ug"};
        String defaultWhere = "u.id = ugu.user_id and ugu.user_group_id = ug.id";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, sortMaps);
    }
}
