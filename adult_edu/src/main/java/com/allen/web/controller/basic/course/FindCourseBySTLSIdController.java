package com.allen.web.controller.basic.course;

import com.allen.service.basic.course.FindCourseBySTLSIdService;
import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/findCourseBySTLSId")
public class FindCourseBySTLSIdController extends BaseController {

    @Autowired
    private FindCourseBySTLSIdService findCourseBySTLSIdService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request, long stlsId) throws Exception {
        request.setAttribute("courseList", findCourseBySTLSIdService.find(stlsId));
        request.setAttribute("reqParams", super.getParameters(request));
        return "/basic/course/courseForSTLS";
    }
}
