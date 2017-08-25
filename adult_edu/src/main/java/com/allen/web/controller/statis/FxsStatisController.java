package com.allen.web.controller.statis;

import com.allen.dao.PageInfo;
import com.allen.entity.user.User;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.eduadmin.student.CountStudentNumForStateWhereByCenterIdAndUserIdService;
import com.allen.service.user.user.*;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/8/22.
 */
@Controller
@RequestMapping(value = "/fxsStatis")
public class FxsStatisController extends BaseController {

    @Autowired
    private CountUserNumForFxsByCenterIdService countUserNumForFxsByCenterIdService;
    @Autowired
    private CountStudentNumForStateWhereByCenterIdAndUserIdService countStudentNumForStateWhereByCenterIdAndUserIdService;
    @Autowired
    private FindUserByCenterIdAndTypeService findUserByCenterIdAndTypeService;
    @Autowired
    private FindSchoolByCenterIdForTeachPlanService findSchoolByCenterIdForTeachPlanService;
    @Autowired
    private PageFxsStudentNumForByCenterIdService pageFxsStudentNumForByCenterIdService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value = "schoolId", required = false) Long schoolId,
                       @RequestParam(value = "recruitTypeId", required = false) Long recruitTypeId,
                       @RequestParam(value = "levelId", required = false) Long levelId,
                       @RequestParam(value = "specId", required = false) Long specId,
                       @RequestParam(value = "teachPlanId", required = false) Long teachPlanId,
                       @RequestParam(value = "userId", required = false) Long userId,
                       HttpServletRequest request) throws Exception {
        long centerId = UserUtil.getLoginUserForCenterId(request);
        //分销商总数
        BigInteger fxsNum = countUserNumForFxsByCenterIdService.find(centerId);
        //分销商招生总数，分销商所招生毕业人数
        Map map = countStudentNumForStateWhereByCenterIdAndUserIdService.find(centerId, -2l);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("u.center_id", centerId);
        params.put("s.school_id", schoolId);
        params.put("s.recruit_type_id", recruitTypeId);
        params.put("s.level_id", levelId);
        params.put("s.spec_id", specId);
        params.put("s.teach_plan_id", teachPlanId);
        params.put("s.user_id", userId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("u.id", true);
        sortMap.put("tp.year", false);
        sortMap.put("tp.term", false);
        PageInfo pageInfo = super.getPageInfo(request);
        pageInfo = pageFxsStudentNumForByCenterIdService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("userList", findUserByCenterIdAndTypeService.find(UserUtil.getLoginUserForCenterId(request), User.TYPE_FXS));
        request.setAttribute("numMap", map);
        request.setAttribute("fxsNum", fxsNum);
        return "/statis/fxs/fxsInfo";
    }
}
