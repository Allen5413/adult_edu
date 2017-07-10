package com.allen.web.controller.basic.spec;

import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
