package com.allen.web.controller.recruit.signup;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.fee.FeeType;
import com.allen.entity.recruit.SignUp;
import com.allen.entity.user.User;
import com.allen.service.basic.level.FindLevelBySchoolIdAndTypeIdForTeachPlanService;
import com.allen.service.basic.level.FindLevelBySchoolIdAndTypeIdService;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeBySchoolIdForTeachPlanService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeBySchoolIdService;
import com.allen.service.eduadmin.teachplan.FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService;
import com.allen.service.fee.feetype.EditFeeTypeService;
import com.allen.service.fee.feetype.FindFeeTypeByIdService;
import com.allen.service.recruit.signup.EditSignUpService;
import com.allen.service.recruit.signup.FindSignUpService;
import com.allen.service.user.user.FindUserByCenterIdAndTypeService;
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
@RequestMapping(value = "/editSignUp")
public class EditSignUpController extends BaseController {

    @Autowired
    private FindSignUpService findSignUpService;
    @Autowired
    private EditSignUpService editSignUpService;
    @Autowired
    private FindSchoolByCenterIdForTeachPlanService findSchoolByCenterIdForTeachPlanService;
    @Autowired
    private FindRecruitTypeBySchoolIdForTeachPlanService findRecruitTypeBySchoolIdForTeachPlanService;
    @Autowired
    private FindLevelBySchoolIdAndTypeIdForTeachPlanService findLevelBySchoolIdAndTypeIdForTeachPlanService;
    @Autowired
    private FindSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService findSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService;
    @Autowired
    private FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService findTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService;
    @Autowired
    private FindUserByCenterIdAndTypeService findUserByCenterIdAndTypeService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam("id")long id)throws Exception{
        SignUp signUp = findSignUpService.find(id);
        request.setAttribute("signUp", signUp);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("typeList", findRecruitTypeBySchoolIdForTeachPlanService.find(signUp.getSchoolId()));
        request.setAttribute("levelList", findLevelBySchoolIdAndTypeIdForTeachPlanService.find(signUp.getSchoolId(), signUp.getRecruitTypeId()));
        request.setAttribute("specList", findSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService.find(signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId()));
        request.setAttribute("tpList", findTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService.find(signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId()));
        request.setAttribute("userList", findUserByCenterIdAndTypeService.find(UserUtil.getLoginUserForCenterId(request), User.TYPE_FXS));
        return "recruit/signup/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, SignUp signUp, String editReson) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != signUp) {
            signUp.setOperator(UserUtil.getLoginUserForName(request));
            editSignUpService.edit(request, signUp, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request), editReson);
        }
        jsonObject.put("state", 0);
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        return jsonObject;
    }
}