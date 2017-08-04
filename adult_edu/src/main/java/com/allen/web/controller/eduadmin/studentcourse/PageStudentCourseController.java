package com.allen.web.controller.eduadmin.studentcourse;

import com.allen.dao.PageInfo;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.eduadmin.studentcourse.PageStudentCourseService;
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
@RequestMapping(value = "/pageStudentCourse")
public class PageStudentCourseController extends BaseController {

    @Autowired
    private PageStudentCourseService pageStudentCourseService;
    @Autowired
    private FindSchoolByCenterIdForTeachPlanService findSchoolByCenterIdForTeachPlanService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "schoolId", required = false) String schoolId,
                       @RequestParam(value = "recruitTypeId", required = false) String recruitTypeId,
                       @RequestParam(value = "levelId", required = false) String levelId,
                       @RequestParam(value = "specId", required = false) String specId,
                       @RequestParam(value = "teachPlanId", required = false) String teachPlanId,
                       @RequestParam(value = "score", required = false) String score,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "courseName", required = false) String courseName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("centerId", UserUtil.getLoginUserForCenterId(request)+"");
        params.put("schoolId", schoolId);
        params.put("recruitTypeId", recruitTypeId);
        params.put("levelId", levelId);
        params.put("specId", specId);
        params.put("teachPlanId", teachPlanId);
        params.put("score", score);
        params.put("name", name);
        params.put("courseName", courseName);
        PageInfo pageInfo = super.getPageInfo(request);
        pageInfo = pageStudentCourseService.find(pageInfo, params);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        return "/eduadmin/studentcourse/page";
    }
}
