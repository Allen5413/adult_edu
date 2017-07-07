package com.allen.web.controller.basic.schooltypelevelspec;

import com.allen.dao.PageInfo;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.basic.schooltypelevelspec.PageSchoolTypeLevelSpecService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByCenterIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Controller
@RequestMapping(value = "/pageSchoolTypeLevelSpec")
public class PageSchoolTypeLevelSpecController extends BaseController {

    @Autowired
    private PageSchoolTypeLevelSpecService pageSchoolTypeLevelSpecService;
    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;
    @Autowired
    private FindRecruitTypeByCenterIdService findRecruitTypeByCenterIdService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "schoolId", required = false)String schoolId,
                       @RequestParam(value = "typeId", required = false)String typeId,
                       @RequestParam(value = "levelId", required = false)String levelId) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, String> params = new HashMap<String, String>();
        params.put("centerId", UserUtil.getLoginUserForCenterId(request)+"");
        params.put("schoolId", schoolId);
        params.put("typeId", typeId);
        params.put("levelId", levelId);
        pageInfo = pageSchoolTypeLevelSpecService.find(pageInfo, params);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("typeList", findRecruitTypeByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("reqParams", super.getParameters(request));
        return "/basic/schooltypelevelspec/page";
    }
}
