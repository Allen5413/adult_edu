package com.allen.web.controller.eduadmin.teachplancourse;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.School;
import com.allen.entity.basic.Spec;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.spec.FindSpecByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import com.allen.service.eduadmin.teachplan.FindTeachPlanByIdService;
import com.allen.service.eduadmin.teachplancourse.ImportTeachPlanCourseService;
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
@RequestMapping(value = "/importTeachPlanCourse")
public class ImportTeachPlanCourseController extends BaseController {

    @Autowired
    private FindTeachPlanByIdService findTeachPlanByIdService;
    @Autowired
    private FindSchoolByIdService findSchoolByIdService;
    @Autowired
    private FindRecruitTypeByIdService findRecruitTypeByIdService;
    @Autowired
    private FindLevelByIdService findLevelByIdService;
    @Autowired
    private FindSpecByIdService findSpecByIdService;
    @Autowired
    private ImportTeachPlanCourseService importTeachPlanCourseService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam(value = "id", required = false)Long id)throws Exception{
        //验证该批次下的学生是否已经存在录入课程成绩的，如果录了就不能再更改批次下的课程了

        TeachPlan teachPlan = findTeachPlanByIdService.find(id);
        School school = findSchoolByIdService.find(teachPlan.getSchoolId());
        RecruitType recruitType = findRecruitTypeByIdService.find(teachPlan.getTypeId());
        Level level = findLevelByIdService.find(teachPlan.getLevelId());
        Spec spec = findSpecByIdService.find(teachPlan.getSpecId());

        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("teachPlan", teachPlan);
        request.setAttribute("school", school);
        request.setAttribute("recruitType", recruitType);
        request.setAttribute("level", level);
        request.setAttribute("spec", spec);
        return "eduadmin/teachplancourse/import";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "importCourse")
    @ResponseBody
    public JSONObject importCourse(HttpServletRequest request, Long id) throws Exception {
        return importTeachPlanCourseService.importCourse(request, id);
    }
}
