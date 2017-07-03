package com.allen.dao.datachange;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/6/30.
 */
@Service
public class FindDataChangeDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, String> paramsMap)throws Exception{
        List<Object> paramsList = new ArrayList<Object>();
        String centerId = paramsMap.get("centerId");
        String studentName = paramsMap.get("studentName");
        String createId = paramsMap.get("createId");
        String state = paramsMap.get("state");
        String beginDate = paramsMap.get("beginDate");
        String endDate = paramsMap.get("endDate");

        String fileds = "dc.id, u.name, u.phone, s.code, s.name studentName, dc.create_time createTime, dc.change_content changeContent, dc.change_table changeTable, dc.change_table_id changeTableId, dc.change_table_field changeTableField, dc.state";
        String sql = "from data_change dc INNER JOIN user u on dc.creator_id = u.id ";
        sql += "LEFT JOIN student s on dc.student_id = s.id ";
        sql += "where dc.center_id = ? ";
        paramsList.add(Long.parseLong(centerId));
        if(!StringUtil.isEmpty(studentName)){
            sql += "and s.name like ? ";
            paramsList.add("%"+studentName+"%");
        }
        if(!StringUtil.isEmpty(createId)){
            sql += "and dc.creator_id = ? ";
            paramsList.add(Long.parseLong(createId));
        }
        if(!StringUtil.isEmpty(state)){
            sql += "and dc.state = ? ";
            paramsList.add(Integer.parseInt(state));
        }
        if(!StringUtil.isEmpty(beginDate)){
            sql += "and dc.create_time >= ? ";
            paramsList.add(beginDate+" 00:00:00");
        }
        if(!StringUtil.isEmpty(endDate)){
            sql += "and dc.create_time <= ? ";
            paramsList.add(endDate+" 23:59:59");
        }
        sql += "order by dc.create_time desc ";

        return super.pageSqlQueryByNativeSqlToMap(pageInfo, sql, fileds, paramsList.toArray());
    }
}
