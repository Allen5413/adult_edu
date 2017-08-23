package com.allen.web.controller.statis;

import com.allen.dao.PageInfo;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.user.User;
import com.allen.service.basic.school.CountSchoolNumByCenterIdService;
import com.allen.service.basic.school.CountSchoolNumForStudentByCenterIdService;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import com.allen.service.basic.spec.FindSpecForNameByCenterIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByCenterIdService;
import com.allen.service.eduadmin.student.PageStudentService;
import com.allen.service.eduadmin.teachplan.CountTeachPlanNumForYearANdTermByCenterIdService;
import com.allen.service.user.user.FindUserByCenterIdAndTypeService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/22.
 */
@Controller
@RequestMapping(value = "/schoolStatis")
public class SchoolStatisController extends BaseController {

    @Autowired
    private PageStudentService pageStudentService;
    @Autowired
    private CountSchoolNumByCenterIdService countSchoolNumByCenterIdService;
    @Autowired
    private CountSchoolNumForStudentByCenterIdService countSchoolNumForStudentByCenterIdService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private FindSpecForNameByCenterIdService findSpecForNameByCenterIdService;
    @Autowired
    private FindRecruitTypeByCenterIdService findRecruitTypeByCenterIdService;
    @Autowired
    private CountTeachPlanNumForYearANdTermByCenterIdService countTeachPlanNumForYearANdTermByCenterIdService;
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
                       HttpServletRequest request) throws Exception {
        long centerId = UserUtil.getLoginUserForCenterId(request);
        //统计高校总数
        BigInteger schoolNum = countSchoolNumByCenterIdService.find(centerId);
        //统计已有学生的高校数
        BigInteger haveStudentSchoolNum = countSchoolNumForStudentByCenterIdService.find(centerId);
        //总学生数
        BigInteger studentNum = studentDao.countNumByCenterId(centerId);
        //总专业数
        List<String> specNameList = findSpecForNameByCenterIdService.find(centerId);
        int specNum = null != specNameList ? specNameList.size() : 0;
        //招生类型数
        List<RecruitType> rtList = findRecruitTypeByCenterIdService.find(centerId);
        int rtNum = null != rtList ? rtList.size() : 0;
        //招生批次数
        BigInteger tpNum = countTeachPlanNumForYearANdTermByCenterIdService.find(centerId);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("s.center_id", UserUtil.getLoginUserForCenterId(request));
        params.put("sc.id", schoolId);
        params.put("rt.id", recruitTypeId);
        params.put("l.id", levelId);
        params.put("sp.id", specId);
        params.put("tp.id", teachPlanId);
        params.put("s.user_id", userId);
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", false);
        pageInfo = pageStudentService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("schoolList", findSchoolByCenterIdForTeachPlanService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("userList", findUserByCenterIdAndTypeService.find(UserUtil.getLoginUserForCenterId(request), User.TYPE_FXS));
        request.setAttribute("schoolNum", schoolNum);
        request.setAttribute("haveStudentSchoolNum", haveStudentSchoolNum);
        request.setAttribute("specNum", specNum);
        request.setAttribute("rtNum", rtNum);
        request.setAttribute("tpNum", tpNum);
        request.setAttribute("studentNum", studentNum);
        return "/statis/school/schoolInfo";
    }
}
