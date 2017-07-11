package com.allen.web.controller.basic.schooltypelevelspeccourse;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.entity.basic.SchoolTypeLevelSpecCourse;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.schooltypelevelspec.FindSchoolTypeLevelSpecByIdService;
import com.allen.service.basic.schooltypelevelspeccourse.AddSchoolTypeLevelSpecCourseService;
import com.allen.service.basic.spec.FindSpecByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addSchoolTypeLevelSpecCourse")
public class AddSchoolTypeLevelSpecCourseController extends BaseController {

    @Autowired
    private AddSchoolTypeLevelSpecCourseService addSchoolTypeLevelSpecCourseService;
    @Autowired
    private FindSchoolTypeLevelSpecByIdService findSchoolTypeLevelSpecByIdService;
    @Autowired
    private FindSchoolByIdService findSchoolByIdService;
    @Autowired
    private FindRecruitTypeByIdService findRecruitTypeByIdService;
    @Autowired
    private FindLevelByIdService findLevelByIdService;
    @Autowired
    private FindSpecByIdService findSpecByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams, long stlsId)throws Exception{
        SchoolTypeLevelSpec schoolTypeLevelSpec = findSchoolTypeLevelSpecByIdService.find(stlsId);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("school", findSchoolByIdService.find(schoolTypeLevelSpec.getSchoolId()));
        request.setAttribute("type", findRecruitTypeByIdService.find(schoolTypeLevelSpec.getRecruitTypeId()));
        request.setAttribute("level", findLevelByIdService.find(schoolTypeLevelSpec.getLevelId()));
        request.setAttribute("spec", findSpecByIdService.find(schoolTypeLevelSpec.getSpecId()));
        return "basic/schooltypelevelspeccourse/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          SchoolTypeLevelSpecCourse schoolTypeLevelSpecCourse,
                          String courseCode, String courseName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != schoolTypeLevelSpecCourse) {
            schoolTypeLevelSpecCourse.setOperator(UserUtil.getLoginUserForName(request));
            addSchoolTypeLevelSpecCourseService.add(schoolTypeLevelSpecCourse, courseCode, courseName);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
