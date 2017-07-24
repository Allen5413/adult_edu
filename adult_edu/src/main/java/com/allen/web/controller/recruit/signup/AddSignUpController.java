package com.allen.web.controller.recruit.signup;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.entity.recruit.SignUp;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.recruit.signup.AddSignUpService;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/19.
 */
@Controller
@RequestMapping(value = "/addSignUp")
public class AddSignUpController extends BaseController {

    @Autowired
    private AddSignUpService addSignUpService;
    @Autowired
    private FindSchoolByCenterIdForTeachPlanService findSchoolByCenterIdForTeachPlanService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request)throws Exception{
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        return "recruit/signup/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, SignUp signUp) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != signUp) {
            signUp.setState(SignUp.STATE_CENTER_WAIT);
            signUp.setCenterId(UserUtil.getLoginUserForCenterId(request));
            signUp.setCerator(UserUtil.getLoginUserForName(request));
            signUp.setOperator(UserUtil.getLoginUserForName(request));
            addSignUpService.add(request, signUp);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
