package com.allen.web.controller.user.user;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.user.user.ResetPwdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/6/29.
 */
@RestController
public class ResetPwdController extends BaseController {

    @Autowired
    private ResetPwdService resetPwdService;

    @RequestMapping(value = "/resetPwd")
    public JSONObject reset(String loginName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        resetPwdService.edit(loginName);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
