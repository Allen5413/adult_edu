package com.allen.web.controller.user.user;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.user.Center;
import com.allen.entity.user.User;
import com.allen.service.user.center.AddCenterService;
import com.allen.service.user.user.AddUserService;
import com.allen.util.MD5Util;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addUser")
public class AddUserController extends BaseController {

    @Autowired
    private AddUserService addUserService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "user/user/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, User user, long userGroupId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != user) {
            user.setCenterId(UserUtil.getLoginUserForCenterId(request));
            user.setLoginName(user.getPhone());
            user.setPwd(MD5Util.MD5(user.getPhone().substring(user.getPhone().length() - 6, user.getPhone().length())));
            user.setState(User.STATE_ENABLE);
            user.setIsOperateAudit(User.ISOPERATEAUDIT_YES);
            user.setCreator(UserUtil.getLoginUserForName(request));
            user.setOperator(UserUtil.getLoginUserForName(request));
            addUserService.add(user, userGroupId);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
