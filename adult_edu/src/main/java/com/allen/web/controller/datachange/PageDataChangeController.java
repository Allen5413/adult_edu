package com.allen.web.controller.datachange;

import com.allen.dao.PageInfo;
import com.allen.service.datachange.PageDataChangeService;
import com.allen.service.user.user.FindUserByCenterIdService;
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
@RequestMapping(value = "/pageDataChange")
public class PageDataChangeController extends BaseController {

    @Autowired
    private PageDataChangeService pageDataChangeService;
    @Autowired
    private FindUserByCenterIdService findUserByCenterIdService;

    @RequestMapping(value = "page")
    public String find(@RequestParam(value="studentName", required=false) String studentName,
                       @RequestParam(value="state", required=false) String state,
                       @RequestParam(value="createId", required=false) String createId,
                       @RequestParam(value="beginDate", required=false) String beginDate,
                       @RequestParam(value="endDate", required=false) String endDate,
                       HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        long centerId = UserUtil.getLoginUserForCenterId(request);
        params.put("centerId", centerId+"");
        params.put("studentName", studentName);
        params.put("state", state);
        params.put("createId", createId);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        PageInfo pageInfo = super.getPageInfo(request);
        pageInfo = pageDataChangeService.find(pageInfo, params);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("userList", findUserByCenterIdService.find(centerId));
        request.setAttribute("reqParams", super.getParameters(request));
        return "/datachange/page";
    }
}
