package com.allen.web.controller.basic.school;

import com.allen.dao.PageInfo;
import com.allen.service.basic.school.PageSchoolService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Controller
@RequestMapping(value = "/pageSchool")
public class PageSchoolController extends BaseController {

    @Autowired
    private PageSchoolService pageSchoolService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("s.center_id", Long.parseLong(UserUtil.getLoginUserForCenterId(request)+""));
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", true);
        pageInfo = pageSchoolService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/basic/school/page";
    }
}
