package com.allen.web.controller.eduadmin.recruittype;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.user.User;
import com.allen.service.eduadmin.recruittype.DelRecruitTypeService;
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
public class DelRecruitTypeController extends BaseController {

    @Autowired
    private DelRecruitTypeService delRecruitTypeService;

    @RequestMapping(value = "/delRecruitType")
    public JSONObject del(@RequestParam("id")long id,
                          HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delRecruitTypeService.del(id, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request));
        jsonObject.put("state", 0);
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        return jsonObject;
    }
}
