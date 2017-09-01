package com.allen.web.controller.notify;

import com.allen.dao.PageInfo;
import com.allen.entity.notify.Notify;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.notify.PageNotifyService;
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
@RequestMapping(value = "/pageNotify")
public class PageNotifyController extends BaseController {

    @Autowired
    private PageNotifyService pageNotifyService;
    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "operateDate", required = false) String operateDate,
                       @RequestParam(value = "type", required = false) String type,
                       @RequestParam(value = "sendObject", required = false) String sendObject,
                       @RequestParam(value = "schoolId", required = false) String schoolId,
                       @RequestParam(value = "specId", required = false) String specId,
                       @RequestParam(value = "teachPlanId", required = false) String teachPlanId,
                       @RequestParam(value = "feeState", required = false) String feeState,
                       @RequestParam(value = "studyState", required = false) String studyState,
                       @RequestParam(value = "stateFlag", required = false) String stateFlag) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("centerId", UserUtil.getLoginUserForCenterId(request)+"");
        params.put("creatorId", UserUtil.getLoginUserForCenterId(request)+"");
        params.put("loginUserType", UserUtil.getLoginUserForType(request)+"");
        params.put("operateDate", operateDate);
        params.put("type", type);
        params.put("sendObject", sendObject);
        params.put("schoolId", schoolId);
        params.put("specId", specId);
        params.put("teachPlanId", teachPlanId);
        params.put("feeState", feeState);
        params.put("studyState", studyState);
        params.put("stateFlag", stateFlag);

        PageInfo pageInfo = super.getPageInfo(request);
        pageInfo = pageNotifyService.find(pageInfo, params);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("reqParams", super.getParameters(request));
        if(Notify.STATE_PASS == Integer.parseInt(stateFlag)){
            return "/notify/pagePass";
        }else {
            return "/notify/page";
        }
    }
}
