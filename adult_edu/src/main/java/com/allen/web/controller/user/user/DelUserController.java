package com.allen.web.controller.user.user;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.user.user.DelUserService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/29.
 */
@RestController
public class DelUserController extends BaseController {

    @Autowired
    private DelUserService delUserService;

    @RequestMapping(value = "/delUser")
    public JSONObject reset(long id, HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delUserService.del(id, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
