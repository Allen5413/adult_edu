package com.allen.web.controller.notify;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.notify.Notify;
import com.allen.entity.user.User;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.notify.AddNotifyService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/30.
 */
@Controller
@RequestMapping(value = "/addNotify")
public class AddNotifyController extends BaseController {

    @Autowired
    private AddNotifyService addNotifyService;
    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request)throws Exception{
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        return "notify/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, Notify notify) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != notify) {
            if(UserUtil.getLoginUserForType(request) == User.TYPE_CENTER_ADMIN){
                notify.setState(Notify.STATE_PASS);
            }else{
                notify.setState(Notify.STATE_WAIT);
            }
            notify.setCenterId(UserUtil.getLoginUserForCenterId(request));
            notify.setCeratorId(UserUtil.getLoginUserForLoginId(request));
            notify.setCerator(UserUtil.getLoginUserForName(request));
            notify.setOperator(UserUtil.getLoginUserForName(request));
            addNotifyService.add(notify);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
