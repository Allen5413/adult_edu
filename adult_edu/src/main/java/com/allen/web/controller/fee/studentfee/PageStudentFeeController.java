package com.allen.web.controller.fee.studentfee;

import com.allen.dao.PageInfo;
import com.allen.entity.user.User;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.fee.studentfee.PageStudentFeeService;
import com.allen.service.user.user.FindUserByCenterIdAndTypeService;
import com.allen.util.StringUtil;
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
@RequestMapping(value = "/pageStudentFee")
public class PageStudentFeeController extends BaseController {

    @Autowired
    private PageStudentFeeService pageStudentFeeService;
    @Autowired
    private FindSchoolByCenterIdForTeachPlanService findSchoolByCenterIdForTeachPlanService;
    @Autowired
    private FindUserByCenterIdAndTypeService findUserByCenterIdAndTypeService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "schoolId", required = false) Long schoolId,
                       @RequestParam(value = "recruitTypeId", required = false) Long recruitTypeId,
                       @RequestParam(value = "levelId", required = false) Long levelId,
                       @RequestParam(value = "specId", required = false) Long specId,
                       @RequestParam(value = "teachPlanId", required = false) Long teachPlanId,
                       @RequestParam(value = "userId", required = false) Long userId,
                       @RequestParam(value = "feeState", required = false) Integer feeState,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "code", required = false) String code) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("s.center_id", UserUtil.getLoginUserForCenterId(request));
        params.put("sc.id", schoolId);
        params.put("rt.id", recruitTypeId);
        params.put("l.id", levelId);
        params.put("sp.id", specId);
        params.put("tp.id", teachPlanId);
        params.put("u.id", userId);
        params.put("s.fee_state", feeState);
        params.put("s.name", StringUtil.isEmpty(name) ? null : new Object[]{"%"+name+"%", "like"});
        params.put("s.code", code);
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", true);
        pageInfo = pageStudentFeeService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("userList", findUserByCenterIdAndTypeService.find(UserUtil.getLoginUserForCenterId(request), User.TYPE_FXS));
        return "/fee/studentfee/page";
    }
}
