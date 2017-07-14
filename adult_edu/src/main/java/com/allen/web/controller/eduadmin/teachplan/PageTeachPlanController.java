package com.allen.web.controller.eduadmin.teachplan;

import com.allen.dao.PageInfo;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.eduadmin.teachplan.PageTeachPlanService;
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
@RequestMapping(value = "/pageTeachPlan")
public class PageTeachPlanController extends BaseController {

    @Autowired
    private PageTeachPlanService pageTeachPlanService;
    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "schoolId", required = false)Long schoolId,
                       @RequestParam(value = "typeId", required = false)Long typeId,
                       @RequestParam(value = "levelId", required = false)Long levelId,
                       @RequestParam(value = "specId", required = false)Long specId,
                       @RequestParam(value = "year", required = false)Integer year,
                       @RequestParam(value = "term", required = false)Integer term) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tp.center_id", UserUtil.getLoginUserForCenterId(request));
        params.put("tp.school_id", schoolId);
        params.put("tp.type_id", typeId);
        params.put("tp.level_id", levelId);
        params.put("tp.spec_id", specId);
        params.put("tp.year", year);
        params.put("tp.term", term);
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("id", true);
        pageInfo = pageTeachPlanService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("reqParams", super.getParameters(request));
        return "/eduadmin/teachplan/page";
    }
}
