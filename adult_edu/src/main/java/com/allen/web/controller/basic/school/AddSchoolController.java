package com.allen.web.controller.basic.school;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.AddSchoolService;
import com.allen.service.user.user.FindUserByCenterIdAndUgIdService;
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
@RequestMapping(value = "/addSchool")
public class AddSchoolController extends BaseController {

    @Autowired
    private AddSchoolService addSchoolService;
    @Autowired
    private FindUserByCenterIdAndUgIdService findUserByCenterIdAndUgIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("userList", findUserByCenterIdAndUgIdService.find(UserUtil.getLoginUserForCenterId(request), 4));
        return "basic/school/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, School school) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != school) {
            school.setCenterId(UserUtil.getLoginUserForCenterId(request));
            school.setCerator(UserUtil.getLoginUserForName(request));
            school.setOperator(UserUtil.getLoginUserForName(request));
            addSchoolService.add(request, school);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
