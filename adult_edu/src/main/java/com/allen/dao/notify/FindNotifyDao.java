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
        String studentPhone = paramsMap.get("studentPhone");
        String fileds = "n.id, n.title, n.type, n.object_remark objectRemark, n.operator, n.operate_time operateTime, n.state";
        String sql = "from notify n ";
        if(!StringUtil.isEmpty(studentPhone)){
            sql += ", student s ";
        }
        sql += "where " + (Notify.STATE_PASS == Integer.parseInt(stateFlag) ? "n.state = " + Notify.STATE_PASS + " " : "n.state != " + Notify.STATE_PASS + " ");
        sql += "and n.center_id = ? ";
        paramsList.add(Long.parseLong(centerId));
        if(!StringUtil.isEmpty(studentPhone)){
            sql += "and s.phone = ? ";
            sql += "and (case when n.school_id is null THEN 1=1 " +
                    "when n.school_id is not null and n.spec_id is null and n.teach_plan_id is null THEN n.school_id = s.school_id " +
                    "when n.school_id is not null and n.spec_id is not null and n.teach_plan_id is null THEN n.school_id = s.school_id and n.spec_id = s.spec_id " +
                    "when n.school_id is not null and n.spec_id is not null and n.teach_plan_id is not null THEN n.school_id = s.school_id and n.spec_id = s.spec_id and n.teach_plan_id = s.teach_plan_id end) " +
                    "and (case WHEN n.fee_state is null THEN 1=1 " +
                    "WHEN n.fee_state is not null THEN n.fee_state = s.fee_state end) " +
                    "and (case WHEN n.study_state is null THEN 1=1 " +
                    "WHEN n.study_state is not null THEN n.study_state = s.state end) ";
            paramsList.add(studentPhone);
        }
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
