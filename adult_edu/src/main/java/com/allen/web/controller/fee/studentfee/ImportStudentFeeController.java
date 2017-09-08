package com.allen.web.controller.fee.studentfee;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.fee.studentfee.ImportStudentFeeService;
import com.allen.service.fee.studentfee.SetCleanFeeStudentService;
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
@RequestMapping(value = "/importStudentFee")
public class ImportStudentFeeController {

    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;
    @Autowired
    private ImportStudentFeeService importStudentFeeService;
    @Autowired
    private SetCleanFeeStudentService setCleanFeeStudentService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        if(!StringUtil.isEmpty(reqParams)) {
            request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        }
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        return "fee/studentfee/import";
    }

    @RequestMapping(value = "/importAdd")
    @ResponseBody
    public JSONObject importAdd(HttpServletRequest request, long schoolId)throws Exception{
        //导入缴费信息导学生缴费表
        JSONObject jsonObject = importStudentFeeService.importStudentFee(request, schoolId);
        //判断学生缴费后，是否已结清费用
        setCleanFeeStudentService.set(jsonObject.get("studentIds").toString());
        return jsonObject;
    }
}
