package com.allen.web.controller.basic.level;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.Level;
import com.allen.entity.user.User;
import com.allen.service.basic.level.EditLevelService;
import com.allen.service.basic.level.FindLevelByIdService;
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
@RequestMapping(value = "/editLevel")
public class EditLevelController extends BaseController {

    @Autowired
    private FindLevelByIdService findLevelByIdService;
    @Autowired
    private EditLevelService editLevelService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam("id")long id)throws Exception{
        request.setAttribute("level", findLevelByIdService.find(id));
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "basic/level/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, Level level, String editReson) throws Exception {
        JSONObject jsonObject = new JSONObject();
        level.setOperator(UserUtil.getLoginUserForName(request));
        editLevelService.edit(level, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request), editReson);
        jsonObject.put("state", 0);
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        return jsonObject;
    }
}
