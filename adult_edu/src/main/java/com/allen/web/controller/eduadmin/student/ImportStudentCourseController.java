package com.allen.web.controller.eduadmin.student;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.eduadmin.studentcourse.ImportStudentCourseService;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
@Controller
@RequestMapping(value = "/importStudentCourse")
public class ImportStudentCourseController {

    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;
    @Autowired
    private ImportStudentCourseService importStudentCourseService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        if(!StringUtil.isEmpty(reqParams)) {
            request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        }
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        return "eduadmin/studentcourse/import";
    }

    @RequestMapping(value = "/importAdd")
    @ResponseBody
    public JSONObject importAdd(HttpServletRequest request, long schoolId)throws Exception{
        JSONObject jsonObject = importStudentCourseService.importScore(request, schoolId);
        return jsonObject;
    }
}
