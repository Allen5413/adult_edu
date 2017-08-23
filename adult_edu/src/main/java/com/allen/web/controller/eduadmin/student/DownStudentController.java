package com.allen.web.controller.eduadmin.student;

import com.allen.dao.PageInfo;
import com.allen.service.eduadmin.student.DownStudentService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2016/5/5.
 */
@Controller
@RequestMapping(value = "/downStudent")
public class DownStudentController extends BaseController {

    @Resource
    private DownStudentService downStudentService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value = "schoolId", required = false) Long schoolId,
                       @RequestParam(value = "recruitTypeId", required = false) Long recruitTypeId,
                       @RequestParam(value = "levelId", required = false) Long levelId,
                       @RequestParam(value = "specId", required = false) Long specId,
                       @RequestParam(value = "teachPlanId", required = false) Long teachPlanId,
                       @RequestParam(value = "userId", required = false) Long userId,
                       HttpServletRequest request)throws Exception{
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
        PageInfo pageInfo = super.getPageInfo(request);
        pageInfo.setCountOfCurrentPage(99999999);
        String downUrl = "/excelDown/school.xls";
        downStudentService.down(pageInfo, params, sortMap, request.getRealPath("") + downUrl);
        return new String(downUrl.getBytes(), "gbk");
    }
}
