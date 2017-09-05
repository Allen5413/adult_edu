package com.allen.web.controller.basic.course;

import com.allen.service.basic.course.FindCourseBySpecIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/findCourseBySpecId")
public class FindCourseBySpecIdController extends BaseController {

    @Autowired
    private FindCourseBySpecIdService findCourseBySpecIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(@RequestParam("specId")long specId,
                       HttpServletRequest request)throws Exception{
        request.setAttribute("courseList", findCourseBySpecIdService.find(specId));
        return "basic/course/courseForSpec";
    }
}
