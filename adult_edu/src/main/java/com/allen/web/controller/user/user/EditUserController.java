package com.allen.web.controller.user.user;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.user.Center;
import com.allen.entity.user.User;
import com.allen.entity.user.UserGroupUser;
import com.allen.service.user.center.EditCenterService;
import com.allen.service.user.center.FindCenterByIdService;
import com.allen.service.user.user.EditUserService;
import com.allen.service.user.user.FindUserByIdService;
import com.allen.service.user.usergroupuser.FindUserGroupUserByUserIdService;
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
@RequestMapping(value = "/editUser")
public class EditUserController extends BaseController {

    @Autowired
    private FindUserByIdService findUserByIdService;
    @Autowired
    private EditUserService editUserService;
    @Autowired
    private FindUserGroupUserByUserIdService findUserGroupUserByUserIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam("id")long id)throws Exception{
        User user = findUserByIdService.find(id);
        UserGroupUser userGroupUser = findUserGroupUserByUserIdService.find(id);
        request.setAttribute("user", user);
        if(null != userGroupUser) {
            request.setAttribute("userGroupId",userGroupUser.getUserGroupId());
        }
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        if(user.getType() == User.TYPE_CENTER_CHAILD) {
            return "user/user/edit";
        }
        if(user.getType() == User.TYPE_FXS){
            return "user/user/editFxs";
        }
        return "";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, User user,
                                @RequestParam(value = "userGroupId", required = false)Long userGroupId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        editUserService.edit(user, userGroupId, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
