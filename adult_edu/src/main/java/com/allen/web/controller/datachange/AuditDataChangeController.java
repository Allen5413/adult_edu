package com.allen.web.controller.datachange;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.datachange.AuditDataChangeService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/3.
 */
@RestController
public class AuditDataChangeController  extends BaseController {

    @Autowired
    private AuditDataChangeService auditDataChangeService;

    @RequestMapping(value = "/auditDataChange")
    public JSONObject del(@RequestParam("id")long id,
                          @RequestParam(value = "refuseContent", required = false)String refuseContent,
                          @RequestParam(value = "state")int state) throws Exception {
        JSONObject jsonObject = new JSONObject();
        auditDataChangeService.audit(id, state, refuseContent);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
