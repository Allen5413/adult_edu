package com.allen.web.controller.notify;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.notify.AuditNotifyService;
import com.allen.service.notify.FindNotifyByIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/9/11.
 */
@Controller
@RequestMapping(value = "/auditNotify")
public class AuditNotifyController extends BaseController {

    @Autowired
    private AuditNotifyService auditNotifyService;
    @Autowired
    private FindNotifyByIdService findNotifyByIdService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, long id, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("notify", findNotifyByIdService.find(id));
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("userType", UserUtil.getLoginUserForType(request));
        return "notify/audit";
    }

    @RequestMapping(value = "pass")
    @ResponseBody
    public JSONObject auditPass(HttpServletRequest request, long id)throws Exception{
        JSONObject jsonObject = new JSONObject();
        auditNotifyService.auditPass(id, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "not")
    @ResponseBody
    public JSONObject auditNot(HttpServletRequest request, long id, String refuseRemark)throws Exception{
        JSONObject jsonObject = new JSONObject();
        auditNotifyService.auditNot(id, refuseRemark, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
