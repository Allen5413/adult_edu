package com.allen.web.controller.eduadmin.recruittype;

import com.allen.dao.PageInfo;
import com.allen.service.eduadmin.recruittype.PageRecruitTypeService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Controller
@RequestMapping(value = "/pageRecruitType")
public class PageRecruitTypeController extends BaseController {

    @Autowired
    private PageRecruitTypeService pageRecruitTypeService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("centerId", UserUtil.getLoginUserForCenterId(request));
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("id", true);
        pageInfo = pageRecruitTypeService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        return "/eduadmin/recruittype/page";
    }
}
