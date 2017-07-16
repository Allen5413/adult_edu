package com.allen.web.controller.basic.spec;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/findSpecBySchoolIdAndTypeIdAndLevelId")
public class FindSpecBySchoolIdAndTypeIdAndLevelIdController extends BaseController {

    @Autowired
    private FindSpecBySchoolIdAndTypeIdAndLevelIdService findSpecBySchoolIdAndTypeIdAndLevelIdService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request, long schoolId, long typeId, long levelId) throws Exception {
        request.setAttribute("specList", findSpecBySchoolIdAndTypeIdAndLevelIdService.find(schoolId, typeId, levelId));
        request.setAttribute("reqParams", super.getParameters(request));
        return "/basic/spec/specForSTL";
    }

    @RequestMapping(value = "findForJSON")
    @ResponseBody
    public JSONObject findForJSON(long schoolId, long typeId, long levelId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("specList", findSpecBySchoolIdAndTypeIdAndLevelIdService.find(schoolId, typeId, levelId));
        return jsonObject;
    }
}
