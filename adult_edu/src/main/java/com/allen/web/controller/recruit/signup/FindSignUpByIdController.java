package com.allen.web.controller.recruit.signup;

import com.allen.entity.basic.Level;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.recruit.SignUp;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.spec.FindSpecByIdService;
import com.allen.service.datachange.FindDataChangeByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import com.allen.service.eduadmin.teachplan.FindTeachPlanByIdService;
import com.allen.service.recruit.signup.FindSignUpByIdService;
import com.allen.service.user.user.FindUserByIdService;
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
@RequestMapping(value = "/findSignUpById")
public class FindSignUpByIdController extends BaseController {

    @Autowired
    private FindDataChangeByIdService findDataChangeByIdService;
    @Autowired
    private FindSignUpByIdService findSignUpByIdService;
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
        SignUp signUp = findSignUpByIdService.find(id);
        if(null != dataChangeId) {
            DataChange dataChange = findDataChangeByIdService.find(dataChangeId);
            request.setAttribute("dataChange", dataChange);
        }
        request.setAttribute("signUp", signUp);
        request.setAttribute("school", findSchoolByIdService.find(signUp.getSchoolId()));
        request.setAttribute("type", findRecruitTypeByIdService.find(signUp.getRecruitTypeId()));
        request.setAttribute("level", findLevelByIdService.find(signUp.getLevelId()));
        request.setAttribute("spec", findSpecByIdService.find(signUp.getSpecId()));
        request.setAttribute("teachPlan", findTeachPlanByIdService.find(signUp.getTeachPlanId()));
        request.setAttribute("user", findUserByIdService.find(signUp.getUserId()));
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "recruit/signup/info";
    }
}
