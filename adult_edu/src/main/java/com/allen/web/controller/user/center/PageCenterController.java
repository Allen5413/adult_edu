package com.allen.web.controller.user.center;

import com.allen.dao.PageInfo;
import com.allen.service.user.center.PageCenterService;
import com.allen.util.DateUtil;
import com.allen.util.StringUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Controller
@RequestMapping(value = "/pageCenter")
public class PageCenterController extends BaseController {

    @Autowired
    private PageCenterService pageCenterService;

    @RequestMapping(value = "page")
    public String find(@RequestParam(value="name", required=false) String name,
                       @RequestParam(value="feeState", required=false) Integer feeState,
                       @RequestParam(value="date", required=false) String date,
                       HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", new Object[]{StringUtil.isEmpty(name) ? "" : "%"+name+"%", "like"});
        params.put("feeState", feeState);
        params.put("authorizeDate", StringUtil.isEmpty(date) ? null : DateUtil.getFormatDate(date+" 23:59:59", DateUtil.longDatePattern));
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("id", true);
        pageInfo = pageCenterService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/user/center/page";
    }
}
