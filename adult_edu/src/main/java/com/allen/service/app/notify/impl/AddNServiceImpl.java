package com.allen.service.app.notify.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.notify.NotifyDao;
import com.allen.entity.notify.Notify;
import com.allen.service.app.notify.AddNService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/9/13.
 */
@Service
public class AddNServiceImpl implements AddNService {

    @Autowired
    private NotifyDao notifyDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        Notify notify = new Notify();
        JSONObject json = new JSONObject();
        String centerId = request.getParameter("centerId");
        String type = request.getParameter("type");
        String sendObject = request.getParameter("sendObject");
        String schoolId = request.getParameter("schoolId");
        String schoolName = StringUtil.getDecode(request, "schoolName");
        String specId = request.getParameter("specId");
        String specName = StringUtil.getDecode(request, "specName");
        String teachPlanId = request.getParameter("teachPlanId");
        String teachPlanName = StringUtil.getDecode(request, "teachPlanName");
        String feeState = request.getParameter("feeState");
        String feeStateName = StringUtil.getDecode(request, "feeStateName");
        String studyState = request.getParameter("studyState");
        String studyStateName = StringUtil.getDecode(request, "studyStateName");
        String title = StringUtil.getDecode(request, "title");
        String content = StringUtil.getDecode(request, "content");
        String ceratorId = request.getParameter("ceratorId");
        String cerator = StringUtil.getDecode(request, "cerator");
        if(StringUtil.isEmpty(centerId)){
            throw new BusinessException("学习中心id不能为空");
        }
        if(StringUtil.isEmpty(type)){
            throw new BusinessException("请选择通知类型");
        }
        if(StringUtil.isEmpty(sendObject)){
            throw new BusinessException("请选择通知对象");
        }
        if(StringUtil.isEmpty(title)){
            throw new BusinessException("标题不能为空");
        }
        if(StringUtil.isEmpty(content)){
            throw new BusinessException("内容不能为空");
        }
        if(StringUtil.isEmpty(ceratorId)){
            throw new BusinessException("操作人id不能为空");
        }
        if(StringUtil.isEmpty(cerator)){
            throw new BusinessException("操作人姓名不能为空");
        }
        if(!StringUtil.isEmpty(schoolId) && StringUtil.isEmpty(schoolName)){
            throw new BusinessException("请传入高校名称");
        }
        if(!StringUtil.isEmpty(specId) && StringUtil.isEmpty(specName)){
            throw new BusinessException("请传入专业名称");
        }
        if(!StringUtil.isEmpty(teachPlanId) && StringUtil.isEmpty(teachPlanName)){
            throw new BusinessException("请传入批次名称");
        }
        if(!StringUtil.isEmpty(feeState) && StringUtil.isEmpty(feeStateName)){
            throw new BusinessException("请传入缴费状态名称");
        }
        if(!StringUtil.isEmpty(studyState) && StringUtil.isEmpty(studyStateName)){
            throw new BusinessException("请传入学籍状态名称");
        }

        notify.setCenterId(Long.parseLong(centerId));
        notify.setTitle(title);
        notify.setContent(content);
        notify.setType(Integer.parseInt(type));
        notify.setSendObject(Integer.parseInt(sendObject));
        notify.setSchoolId(StringUtil.isEmpty(schoolId) ? null : Long.parseLong(schoolId));
        notify.setSpecId(StringUtil.isEmpty(specId) ? null : Long.parseLong(specId));
        notify.setTeachPlanId(StringUtil.isEmpty(teachPlanId) ? null : Long.parseLong(teachPlanId));
        notify.setFeeState(StringUtil.isEmpty(feeState) ? null : Integer.parseInt(feeState));
        notify.setStudyState(StringUtil.isEmpty(studyState) ? null : Integer.parseInt(studyState));
        notify.setState(Notify.STATE_PASS);
        notify.setCeratorId(Long.parseLong(ceratorId));
        notify.setCerator(cerator);
        notify.setOperator(cerator);

        String objectRemark = "";
        if ("0".equals(sendObject)) {
            if (StringUtil.isEmpty(schoolName) && StringUtil.isEmpty(specName) && StringUtil.isEmpty(teachPlanName) && StringUtil.isEmpty(feeStateName) && StringUtil.isEmpty(studyStateName)) {
                objectRemark = "所有学生";
            } else {
                objectRemark = schoolName + (StringUtil.isEmpty(specName) ? "" : specName) + (StringUtil.isEmpty(teachPlanName) ? "" : teachPlanName) + (StringUtil.isEmpty(feeStateName) ? "" : feeStateName) + (StringUtil.isEmpty(studyStateName) ? "" : studyStateName) + "的学生";
            }
        }
        if ("1".equals(sendObject)) {
            objectRemark = "所有分销商";
        }
        notify.setObjectRemark(objectRemark);

        notifyDao.save(notify);
        json.put("status", 1);
        return json;
    }
}
