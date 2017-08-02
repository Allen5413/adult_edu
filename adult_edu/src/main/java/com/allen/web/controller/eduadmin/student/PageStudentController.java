package com.allen.web.controller.eduadmin.student;

import com.allen.dao.PageInfo;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.eduadmin.student.PageStudentService;
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
@RequestMapping(value = "/pageStudent")
public class PageStudentController extends BaseController {

    @Autowired
    private PageStudentService pageStudentService;
    @Autowired
    private FindSchoolByCenterIdForTeachPlanService findSchoolByCenterIdForTeachPlanService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "schoolId", required = false) Long schoolId,
                       @RequestParam(value = "recruitTypeId", required = false) Long recruitTypeId,
                       @RequestParam(value = "levelId", required = false) Long levelId,
                       @RequestParam(value = "specId", required = false) Long specId,
                       @RequestParam(value = "teachPlanId", required = false) Long teachPlanId,
                       @RequestParam(value = "state", required = false) Integer state,
                       @RequestParam(value = "name", required = false) String name) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("s.center_id", UserUtil.getLoginUserForCenterId(request));
        params.put("sc.id", schoolId);
        params.put("rt.id", recruitTypeId);
        params.put("l.id", levelId);
        params.put("sp.id", specId);
        params.put("tp.id", teachPlanId);
        params.put("s.state", state);
        params.put("s.name", StringUtil.isEmpty(name) ? null : new Object[]{"%"+name+"%", "like"});
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", true);
        pageInfo = pageStudentService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        return "/eduadmin/student/page";
    }
}
