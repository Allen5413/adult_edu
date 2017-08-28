package com.allen.web.controller.statis;

import com.allen.dao.PageInfo;
import com.allen.service.eduadmin.student.PageStudentService;
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
 * Created by Allen on 2017/8/28.
 */
@Controller
@RequestMapping(value = "/findStudentForFxsStatis")
public class FindStudentForFxsStatisController extends BaseController {

    @Autowired
    private PageStudentService pageStudentService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value = "schoolId", required = false) Long schoolId,
                       @RequestParam(value = "recruitTypeId", required = false) Long recruitTypeId,
                       @RequestParam(value = "levelId", required = false) Long levelId,
                       @RequestParam(value = "specId", required = false) Long specId,
                       @RequestParam(value = "teachPlanId", required = false) Long teachPlanId,
                       @RequestParam(value = "userId", required = false) Long userId,
                       HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("s.center_id", UserUtil.getLoginUserForCenterId(request));
        params.put("sc.id", schoolId);
        params.put("rt.id", recruitTypeId);
        params.put("l.id", levelId);
        params.put("sp.id", specId);
        params.put("tp.id", teachPlanId);
        params.put("s.user_id", userId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", false);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(1);
        pageInfo.setCountOfCurrentPage(999999);
        pageInfo = pageStudentService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        return "/statis/fxs/studentList";
    }
}
