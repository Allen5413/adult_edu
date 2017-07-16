package com.allen.web.controller.eduadmin.teachplancourse;

import com.allen.service.eduadmin.teachplancourse.FindTeachPlanCourseByTeachPlanIdService;
import com.allen.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/6/4.
 */
@Controller
@RequestMapping(value = "/findTeachPlanCourseByTeachPlanId")
public class FindTeachPlanCourseByTeachPlanIdController extends BaseController {

    @Resource
    private FindTeachPlanCourseByTeachPlanIdService findTeachPlanCourseByTeachPlanIdService;


    @RequestMapping(value = "find")
    public String find(Long teachPlanId, HttpServletRequest request)throws Exception{
        request.setAttribute("list", findTeachPlanCourseByTeachPlanIdService.find(teachPlanId));
        return "/eduadmin/teachplancourse/list";
    }
}