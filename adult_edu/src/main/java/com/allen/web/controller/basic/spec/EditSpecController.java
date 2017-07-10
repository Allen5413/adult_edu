package com.allen.web.controller.basic.spec;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.Spec;
import com.allen.entity.user.User;
import com.allen.service.basic.spec.EditSpecService;
import com.allen.service.basic.spec.FindSpecByIdService;
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
@RequestMapping(value = "/editSpec")
public class EditSpecController extends BaseController {

    @Autowired
    private EditSpecService editSpecService;
    @Autowired
    private FindSpecByIdService findSpecByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams, long id)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("spec", findSpecByIdService.find(id));
        return "basic/spec/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject edit(HttpServletRequest request, Spec spec,
                           @RequestParam(value = "editReson", required = false)String editReson) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != spec) {
            spec.setCreator(UserUtil.getLoginUserForName(request));
            spec.setOperator(UserUtil.getLoginUserForName(request));
            editSpecService.edit(spec, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request), editReson);
        }
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
