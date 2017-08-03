package com.allen.web.controller.fee.studentfee;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.fee.StudentFee;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.eduadmin.student.FindStudentByIdService;
import com.allen.service.eduadmin.teachplan.FindTeachPlanByIdService;
import com.allen.service.fee.feetype.FindFeeTypeBySchoolIdAndTypeIdAndLevelIdAndYearAndTermService;
import com.allen.service.fee.studentfee.AddStudentFeeService;
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
@RequestMapping(value = "/addStudentFee")
public class AddStudentFeeController extends BaseController {

    @Autowired
    private AddStudentFeeService addStudentFeeService;
    @Autowired
    private FindStudentByIdService findStudentByIdService;
    @Autowired
    private FindSchoolByIdService findSchoolByIdService;
    @Autowired
    private FindUserByIdService findUserByIdService;
    @Autowired
    private FindTeachPlanByIdService findTeachPlanByIdService;
    @Autowired
    private FindFeeTypeBySchoolIdAndTypeIdAndLevelIdAndYearAndTermService findFeeTypeBySchoolIdAndTypeIdAndLevelIdAndYearAndTermService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams, long studentId)throws Exception{
        //查询学生拥有哪些缴费类型
        Student student = findStudentByIdService.find(studentId);
        if(null == student){
            throw new BusinessException("没有找到学生信息");
        }
        TeachPlan teachPlan = findTeachPlanByIdService.find(student.getTeachPlanId());
        if(null == teachPlan){
            throw new BusinessException("没有找到该学生的教学计划");
        }
        request.setAttribute("typeList", findFeeTypeBySchoolIdAndTypeIdAndLevelIdAndYearAndTermService.find(student.getSchoolId(), student.getRecruitTypeId(), student.getLevelId(), teachPlan.getYear(), teachPlan.getTerm()));
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("school", findSchoolByIdService.find(student.getSchoolId()));
        request.setAttribute("user", findUserByIdService.find(student.getUserId()));
        request.setAttribute("student", student);
        return "fee/studentfee/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, StudentFee studentFee, double money,
                          @RequestParam(value = "fxsMoney")Long fxsMoney) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != studentFee) {
            studentFee.setFee((long)money*100);
            if(null != fxsMoney) {
                studentFee.setFxsFee((long)fxsMoney*100);
            }
            studentFee.setOperator(UserUtil.getLoginUserForName(request));
            addStudentFeeService.add(studentFee);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
