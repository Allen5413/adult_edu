package com.allen.service.app.datachange.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.service.app.datachange.AuditDCService;
import com.allen.service.datachange.AuditDataChangeService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/14.
 */
@Service
public class AuditDCServiceImpl implements AuditDCService {

    @Autowired
    private AuditDataChangeService auditDataChangeService;

    @Override
    public JSONObject audit(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        String state = request.getParameter("state");
        String refuseContent = StringUtil.getDecode(request, "refuseContent");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入id");
        }
        if(StringUtil.isEmpty(state)){
            throw new BusinessException("没有传入审核状态");
        }
        if("1".equals(state) && StringUtil.isEmpty(refuseContent)){
            throw new BusinessException("没有传入拒绝理由");
        }
        auditDataChangeService.audit(request, Long.parseLong(id), Integer.parseInt(state), refuseContent);
        json.put("status", 1);
        return json;
    }
}
