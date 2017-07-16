package com.allen.web.controller.eduadmin.teachplan;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.user.User;
import com.allen.service.eduadmin.teachplan.DelTeachPlanByIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/29.
 */
@RestController
public class DelTeachPlanController extends BaseController {

    @Autowired
    private DelTeachPlanByIdService delTeachPlanByIdService;

    @RequestMapping(value = "/delTeachPlan")
    public JSONObject del(@RequestParam("id")long id,
                          @RequestParam(value = "editReson", required = false)String editReson,
                          HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delTeachPlanByIdService.del(id, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request), editReson);
        jsonObject.put("state", 0);
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        return jsonObject;
    }
}
