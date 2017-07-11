package com.allen.web.controller.basic.schooltypelevelspeccourse;

import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.entity.basic.SchoolTypeLevelSpecCourse;
import com.allen.entity.datachange.DataChange;
import com.allen.service.basic.course.FindCourseByIdService;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.schooltypelevelspec.FindSchoolTypeLevelSpecByIdService;
import com.allen.service.basic.schooltypelevelspeccourse.FindSchoolTypeLevelSpecCourseByIdService;
import com.allen.service.basic.spec.FindSpecByIdService;
import com.allen.service.datachange.FindDataChangeByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
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
@RequestMapping(value = "/findSchoolTypeLevelSpecCourseById")
public class FindSchoolTypeLevelSpecCourseByIdController extends BaseController {

    @Autowired
    private FindDataChangeByIdService findDataChangeByIdService;
    @Autowired
    private FindSchoolTypeLevelSpecCourseByIdService findSchoolTypeLevelSpecCourseByIdService;
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
    @Autowired
    private FindCourseByIdService findCourseByIdService;


    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(@RequestParam("id")long id,
                       @RequestParam(value = "dataChangeId", required = false)Long dataChangeId,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       HttpServletRequest request)throws Exception{
        SchoolTypeLevelSpecCourse schoolTypeLevelSpecCourse = findSchoolTypeLevelSpecCourseByIdService.find(id);
        SchoolTypeLevelSpec schoolTypeLevelSpec = findSchoolTypeLevelSpecByIdService.find(schoolTypeLevelSpecCourse.getSchoolTypeLevelSpecId());
        if(null != dataChangeId) {
            DataChange dataChange = findDataChangeByIdService.find(dataChangeId);
            request.setAttribute("dataChange", dataChange);
        }
        request.setAttribute("schoolTypeLevelSpecCourse", schoolTypeLevelSpecCourse);
        request.setAttribute("school", findSchoolByIdService.find(schoolTypeLevelSpec.getSchoolId()));
        request.setAttribute("recruitType", findRecruitTypeByIdService.find(schoolTypeLevelSpec.getRecruitTypeId()));
        request.setAttribute("level", findLevelByIdService.find(schoolTypeLevelSpec.getLevelId()));
        request.setAttribute("spec", findSpecByIdService.find(schoolTypeLevelSpec.getSpecId()));
        request.setAttribute("course", findCourseByIdService.find(schoolTypeLevelSpecCourse.getCourseId()));
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "basic/schooltypelevelspeccourse/info";
    }
}
