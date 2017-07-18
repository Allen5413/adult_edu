package com.allen.web.controller.user.user;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.user.user.EditPwdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/18.
 */
@Controller
@RequestMapping(value = "/editPwd")
public class EditPwdController extends BaseController {

    @Autowired
    private EditPwdService editPwdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open()throws Exception{
        return "user/user/editPwd";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, String oldPwd, String newPwd) throws Exception {
        JSONObject jsonObject = new JSONObject();
        editPwdService.edit(oldPwd, newPwd, UserUtil.getLoginUserForLoginId(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
