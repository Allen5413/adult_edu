package com.allen.web.controller.user.user;

import com.allen.dao.PageInfo;
import com.allen.service.datachange.PageDataChangeService;
import com.allen.service.user.user.FindUserByCenterIdService;
import com.allen.service.user.user.PageUserService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Controller
@RequestMapping(value = "/pageUser")
public class PageUserController extends BaseController {

    @Autowired
    private PageUserService pageUserService;

    @RequestMapping(value = "page")
    public String find(@RequestParam(value="type", required=false) String type,
                       @RequestParam(value="state", required=false) String state,
                       @RequestParam(value="name", required=false) String name,
                       HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        long centerId = UserUtil.getLoginUserForCenterId(request);
        params.put("centerId", centerId+"");
        params.put("name", name);
        params.put("state", state);
        params.put("type", type);
        PageInfo pageInfo = super.getPageInfo(request);
        pageInfo = pageUserService.find(pageInfo, params);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        if("2".equals(type)){
            return "/user/user/page";
        }
        if("3".equals(type)){
            return "/user/user/fxsPage";
        }
        return "";
    }
}
