package com.allen.web.controller.eduadmin.student;

import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.recruit.SignUp;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.spec.FindSpecByIdService;
import com.allen.service.datachange.FindDataChangeByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import com.allen.service.eduadmin.student.FindStudentByIdService;
import com.allen.service.eduadmin.teachplan.FindTeachPlanByIdService;
import com.allen.service.recruit.signup.FindSignUpByIdService;
import com.allen.service.user.user.FindUserByIdService;
import com.allen.util.StringUtil;
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
@RequestMapping(value = "/findStudentById")
public class FindStudentByIdController extends BaseController {

    @Autowired
    private FindDataChangeByIdService findDataChangeByIdService;
    @Autowired
    private FindStudentByIdService findStudentByIdService;
    @Autowired
    private FindSchoolByIdService findSchoolByIdService;
    @Autowired
    private FindRecruitTypeByIdService findRecruitTypeByIdService;
    @Autowired
    private FindLevelByIdService findLevelByIdService;
    @Autowired
    private FindSpecByIdService findSpecByIdService;
    @Autowired
    private FindTeachPlanByIdService findTeachPlanByIdService;
    @Autowired
    private FindUserByIdService findUserByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(@RequestParam("id")long id,
                       @RequestParam(value = "dataChangeId", required = false)Long dataChangeId,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       HttpServletRequest request)throws Exception{
        Student student = findStudentByIdService.find(id);
        if(null != dataChangeId) {
            DataChange dataChange = findDataChangeByIdService.find(dataChangeId);
            request.setAttribute("dataChange", dataChange);
        }
        request.setAttribute("student", student);
        request.setAttribute("school", findSchoolByIdService.find(student.getSchoolId()));
        request.setAttribute("type", findRecruitTypeByIdService.find(student.getRecruitTypeId()));
        request.setAttribute("level", findLevelByIdService.find(student.getLevelId()));
        request.setAttribute("spec", findSpecByIdService.find(student.getSpecId()));
        request.setAttribute("teachPlan", findTeachPlanByIdService.find(student.getTeachPlanId()));
        request.setAttribute("user", findUserByIdService.find(student.getUserId()));
        request.setAttribute("reqParams", StringUtil.isEmpty(reqParams) ? "" : new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "eduadmin/student/info";
    }
}
