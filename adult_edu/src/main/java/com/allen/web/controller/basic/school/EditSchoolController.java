package com.allen.web.controller.basic.school;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.EditSchoolService;
import com.allen.service.basic.school.FindSchoolByIdService;
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
@RequestMapping(value = "/editSchool")
public class EditSchoolController extends BaseController {

    @Autowired
    private FindSchoolByIdService findSchoolByIdService;
    @Autowired
    private EditSchoolService editSchoolService;
    @Autowired
    private FindUserByCenterIdAndUgIdService findUserByCenterIdAndUgIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam("id")long id)throws Exception{
        School school = findSchoolByIdService.find(id);
        request.setAttribute("school", school);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("userList", findUserByCenterIdAndUgIdService.find(UserUtil.getLoginUserForCenterId(request), 4));
        return "basic/school/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, School school) throws Exception {
        JSONObject jsonObject = new JSONObject();
        editSchoolService.edit(school, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
