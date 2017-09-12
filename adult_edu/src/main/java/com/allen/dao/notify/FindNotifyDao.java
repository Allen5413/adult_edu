package com.allen.dao.notify;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.entity.notify.Notify;
import com.allen.entity.user.User;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/30.
 */
@Service
public class FindNotifyDao extends BaseQueryDao {

    public PageInfo findPage(PageInfo pageInfo, Map<String, String> paramsMap)throws Exception{
        List<Object> paramsList = new ArrayList<Object>();
        String centerId = paramsMap.get("centerId");
        String creatorId = paramsMap.get("creatorId");
        String operateDate = paramsMap.get("operateDate");
        String type = paramsMap.get("type");
        String sendObject = paramsMap.get("sendObject");
        String schoolId = paramsMap.get("schoolId");
        String specId = paramsMap.get("specId");
        String teachPlanId = paramsMap.get("teachPlanId");
        String feeState = paramsMap.get("feeState");
        String studyState = paramsMap.get("studyState");
        String stateFlag = paramsMap.get("stateFlag");
        String loginUserType = paramsMap.get("loginUserType");
        String fileds = "n.id, n.title, n.type, n.object_remark objectRemark, n.operator, n.operate_time, n.state";
        String sql = "from notify n ";
        sql += "where " + (Notify.STATE_PASS == Integer.parseInt(stateFlag) ? "n.state = " + Notify.STATE_PASS + " " : "n.state != " + Notify.STATE_PASS + " ");
        sql += "and n.center_id = ? ";
        paramsList.add(Long.parseLong(centerId));
        if(!StringUtil.isEmpty(loginUserType) && User.TYPE_CENTER_CHAILD == Integer.parseInt(loginUserType)){
            sql += "and n.cerator_id = ? ";
            paramsList.add(Long.parseLong(creatorId));
        }
        if(!StringUtil.isEmpty(operateDate)){
            sql += "and n.operate_time between ? and ? ";
            paramsList.add(operateDate+" 00:00:00");
            paramsList.add(operateDate+" 23:59:59");
        }
        if(!StringUtil.isEmpty(type)){
            sql += "and n.type = ? ";
            paramsList.add(Integer.parseInt(type));
        }
        if(!StringUtil.isEmpty(sendObject)){
            sql += "and n.send_object = ? ";
            paramsList.add(Integer.parseInt(sendObject));
        }
        if(!StringUtil.isEmpty(schoolId)){
            sql += "and n.school_id = ? ";
            paramsList.add(Long.parseLong(schoolId));
        }
        if(!StringUtil.isEmpty(specId)){
            sql += "and n.spec_id = ? ";
            paramsList.add(Long.parseLong(specId));
        }
        if(!StringUtil.isEmpty(teachPlanId)){
            sql += "and n.teach_plan_id = ? ";
            paramsList.add(Long.parseLong(teachPlanId));
        }
        if(!StringUtil.isEmpty(feeState)){
            sql += "and n.fee_state = ? ";
            paramsList.add(Integer.parseInt(feeState));
        }
        if(!StringUtil.isEmpty(studyState)){
            sql += "and n.study_state = ? ";
            paramsList.add(Integer.parseInt(studyState));
        }
        sql += "order by n.id desc ";

        return super.pageSqlQueryByNativeSqlToMap(pageInfo, sql, fileds, paramsList.toArray());
    }
}
