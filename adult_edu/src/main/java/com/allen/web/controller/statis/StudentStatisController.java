package com.allen.web.controller.statis;

import com.allen.dao.PageInfo;
import com.allen.entity.user.User;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.eduadmin.student.CountStudentNumForStateWhereByCenterIdAndUserIdService;
import com.allen.service.eduadmin.student.PageStudentService;
import com.allen.service.user.user.FindUserByCenterIdAndTypeService;
import com.allen.service.user.user.FindUserByCenterIdAndUgIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/22.
 */
@Controller
@RequestMapping(value = "/studentStatis")
public class StudentStatisController extends BaseController {

    @Autowired
    private PageStudentService pageStudentService;
    @Autowired
    private CountStudentNumForStateWhereByCenterIdAndUserIdService countStudentNumForStateWhereByCenterIdAndUserIdService;
    @Autowired
    private FindUserByCenterIdAndUgIdService findUserByCenterIdAndUgIdService;
    @Autowired
    private FindSchoolByCenterIdForTeachPlanService findSchoolByCenterIdForTeachPlanService;
    @Autowired
    private FindUserByCenterIdAndTypeService findUserByCenterIdAndTypeService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value = "schoolId", required = false) Long schoolId,
                       @RequestParam(value = "recruitTypeId", required = false) Long recruitTypeId,
                       @RequestParam(value = "levelId", required = false) Long levelId,
                       @RequestParam(value = "specId", required = false) Long specId,
                       @RequestParam(value = "teachPlanId", required = false) Long teachPlanId,
                       @RequestParam(value = "userId", required = false) Long userId,
                       @RequestParam(value = "userId2", required = false) Long userId2,
                       @RequestParam(value = "state", required = false) Integer state,
                       @RequestParam(value = "feeState", required = false) Integer feeState,
                       HttpServletRequest request) throws Exception {
        long centerId = UserUtil.getLoginUserForCenterId(request);
        //统计学生总数，在籍人数，毕业人数，休学人数，费用未结清人数
        Map msp = countStudentNumForStateWhereByCenterIdAndUserIdService.find(centerId, null);
        //教务老师人数
        List<User> userList = findUserByCenterIdAndUgIdService.find(centerId, 4);
        int jwNum = null == userList ? 0 : userList.size();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("s.center_id", UserUtil.getLoginUserForCenterId(request));
        params.put("sc.id", schoolId);
        params.put("rt.id", recruitTypeId);
        params.put("l.id", levelId);
        params.put("sp.id", specId);
        params.put("tp.id", teachPlanId);
        params.put("s.user_id", userId);
        params.put("sc.user_id", userId2);
        params.put("s.state", state);
        params.put("s.fee_state", feeState);
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", false);
        pageInfo = pageStudentService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("userList", findUserByCenterIdAndTypeService.find(UserUtil.getLoginUserForCenterId(request), User.TYPE_FXS));
        request.setAttribute("jwUserList", userList);
        request.setAttribute("numMap", msp);
        request.setAttribute("jwNum", jwNum);
        return "/statis/student/studentInfo";
    }
}
