package com.allen.web.controller.eduadmin.student;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.user.User;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.spec.FindSpecByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import com.allen.service.eduadmin.student.EditStudentService;
import com.allen.service.eduadmin.student.FindStudentByIdService;
import com.allen.service.eduadmin.teachplan.FindTeachPlanByIdService;
import com.allen.service.user.user.FindUserByIdService;
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
@RequestMapping(value = "/editStudent")
public class EditStudentController extends BaseController {

    @Autowired
    private FindStudentByIdService findStudentByIdService;
    @Autowired
    private EditStudentService editStudentService;
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
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam("id")long id)throws Exception{
        Student student = findStudentByIdService.find(id);
        request.setAttribute("student", student);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("school", findSchoolByIdService.find(student.getSchoolId()));
        request.setAttribute("type", findRecruitTypeByIdService.find(student.getRecruitTypeId()));
        request.setAttribute("level", findLevelByIdService.find(student.getLevelId()));
        request.setAttribute("spec", findSpecByIdService.find(student.getSpecId()));
        request.setAttribute("tp", findTeachPlanByIdService.find(student.getTeachPlanId()));
        request.setAttribute("user", findUserByIdService.find(student.getUserId()));
        return "eduadmin/student/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, Student student, String editReson) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != student) {
            student.setOperator(UserUtil.getLoginUserForName(request));
            editStudentService.edit(request, student, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request), editReson);
        }
        jsonObject.put("state", 0);
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        return jsonObject;
    }
}
