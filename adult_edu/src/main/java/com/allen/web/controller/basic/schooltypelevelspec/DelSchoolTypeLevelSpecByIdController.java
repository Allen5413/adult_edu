package com.allen.web.controller.basic.schooltypelevelspec;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.user.User;
import com.allen.service.basic.schooltypelevelspec.DelSchoolTypeLevelSpecByIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/10.
 */
@RestController
public class DelSchoolTypeLevelSpecByIdController extends BaseController {

    @Autowired
    private DelSchoolTypeLevelSpecByIdService delSchoolTypeLevelSpecByIdService;

    @RequestMapping(value = "/delSchoolTypeLevelSpecById")
    public JSONObject del(@RequestParam("id")long id,
                          @RequestParam("specId")long specId,
                          @RequestParam(value = "editReson", required = false)String editReson,
                          HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delSchoolTypeLevelSpecByIdService.del(id, specId, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request), editReson);
        jsonObject.put("state", 0);
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        return jsonObject;
    }
}
