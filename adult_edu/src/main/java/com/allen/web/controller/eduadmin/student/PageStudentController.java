package com.allen.web.controller.eduadmin.student;

import com.allen.dao.PageInfo;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.eduadmin.student.PageStudentService;
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
@RequestMapping(value = "/pageStudent")
public class PageStudentController extends BaseController {

    @Autowired
    private PageStudentService pageStudentService;
    @Autowired
    private FindSchoolByCenterIdForTeachPlanService findSchoolByCenterIdForTeachPlanService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("s.center_id", UserUtil.getLoginUserForCenterId(request));
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", true);
        pageInfo = pageStudentService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        return "/eduadmin/student/page";
    }
}
