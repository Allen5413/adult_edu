package com.allen.web.controller.recruit.signup;

import com.allen.dao.PageInfo;
import com.allen.service.recruit.signup.PageSignUpService;
import com.allen.util.StringUtil;
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
@RequestMapping(value = "/pageSignUp")
public class PageSignUpController extends BaseController {

    @Autowired
    private PageSignUpService pageSignUpService;

    @RequestMapping(value = "page")
    public String page(HttpServletRequest request,
                       @RequestParam(value = "name", required = false)String name,
                       @RequestParam(value = "state", required = false)Integer state) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("su.centerId", Long.parseLong(UserUtil.getLoginUserForCenterId(request)+""));
        params.put("su.name", new Object[]{StringUtil.isEmpty(name) ? "" : "%"+name+"%", "like"});
        params.put("su.state", state);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("id", false);
        pageInfo = pageSignUpService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/recruit/signup/page";
    }
}
