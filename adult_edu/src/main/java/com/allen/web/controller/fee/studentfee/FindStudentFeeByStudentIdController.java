package com.allen.web.controller.fee.studentfee;

import com.allen.entity.eduadmin.Student;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.eduadmin.student.FindStudentByIdService;
import com.allen.service.fee.studentfee.FindStudentFeeByStudentIdService;
import com.allen.service.user.user.FindUserByIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/findStudentFeeByStudentId")
public class FindStudentFeeByStudentIdController extends BaseController {

    @Autowired
    private FindStudentFeeByStudentIdService findStudentFeeByStudentIdService;
    @Autowired
    private FindStudentByIdService findStudentByIdService;
    @Autowired
    private FindSchoolByIdService findSchoolByIdService;
    @Autowired
    private FindUserByIdService findUserByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(Long studentId, HttpServletRequest request)throws Exception{
        Student student = findStudentByIdService.find(studentId);
        request.setAttribute("feeDetail", findStudentFeeByStudentIdService.find(studentId));
        request.setAttribute("school", findSchoolByIdService.find(student.getSchoolId()));
        request.setAttribute("user", findUserByIdService.find(student.getUserId()));
        request.setAttribute("student", student);
        return "fee/studentfee/detail";
    }
}
