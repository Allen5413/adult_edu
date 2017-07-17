package com.allen.web.controller.fee.feetype;

import com.allen.dao.PageInfo;
import com.allen.service.fee.feetype.PageFeeTypeService;
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
@RequestMapping(value = "/pageFeeType")
public class PageFeeTypeController extends BaseController {

    @Autowired
    private PageFeeTypeService pageFeeTypeService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ft.center_id", UserUtil.getLoginUserForCenterId(request));
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("ft.id", false);
        pageInfo = pageFeeTypeService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/fee/feetype/page";
    }
}
