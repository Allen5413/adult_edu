package com.allen.web.controller.eduadmin.teachplan;

import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.spec.FindSpecByIdService;
import com.allen.service.datachange.FindDataChangeByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import com.allen.service.eduadmin.teachplan.FindTeachPlanByIdService;
import com.allen.service.eduadmin.teachplancourse.FindTeachPlanCourseByTeachPlanIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/findTeachPlanById")
public class FindTeachPlanByIdController extends BaseController {

    @Autowired
    private FindDataChangeByIdService findDataChangeByIdService;
    @Autowired
    private FindTeachPlanByIdService findTeachPlanByIdService;
    @Autowired
    private FindTeachPlanCourseByTeachPlanIdService findTeachPlanCourseByTeachPlanIdService;
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
    public String open(@RequestParam("id")long id,
                       @RequestParam(value = "dataChangeId", required = false)Long dataChangeId,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       HttpServletRequest request)throws Exception{
        TeachPlan teachPlan = findTeachPlanByIdService.find(id);
        List<Map> courseList = findTeachPlanCourseByTeachPlanIdService.find(teachPlan.getId());
        if(null != dataChangeId) {
            DataChange dataChange = findDataChangeByIdService.find(dataChangeId);
            request.setAttribute("dataChange", dataChange);
        }
        request.setAttribute("teachPlan", teachPlan);
        request.setAttribute("school", findSchoolByIdService.find(teachPlan.getSchoolId()));
        request.setAttribute("type", findRecruitTypeByIdService.find(teachPlan.getTypeId()));
        request.setAttribute("level", findLevelByIdService.find(teachPlan.getLevelId()));
        request.setAttribute("spec", findSpecByIdService.find(teachPlan.getSpecId()));
        request.setAttribute("courseList", courseList);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "eduadmin/teachplan/info";
    }
}
