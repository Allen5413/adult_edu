package com.allen.web.controller.basic.level;

import com.allen.dao.PageInfo;
import com.allen.service.basic.level.PageLevelService;
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
@RequestMapping(value = "/pageLevel")
public class PageLevelController extends BaseController {

    @Autowired
    private PageLevelService pageLevelService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("centerId", Long.parseLong(UserUtil.getLoginUserForCenterId(request)+""));
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("id", true);
        pageInfo = pageLevelService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/basic/level/page";
    }
}
