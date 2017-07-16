package com.allen.web.controller.eduadmin.teachplan;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.service.basic.level.FindLevelBySchoolIdAndTypeIdService;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeBySchoolIdService;
import com.allen.service.eduadmin.teachplan.AddTeachPlanService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addTeachPlan")
public class AddTeachPlanController extends BaseController {

    @Autowired
    private AddTeachPlanService addTeachPlanService;
    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;
    @Autowired
    private FindRecruitTypeBySchoolIdService findRecruitTypeBySchoolIdService;
    @Autowired
    private FindLevelBySchoolIdAndTypeIdService findLevelBySchoolIdAndTypeIdService;
    @Autowired
    private FindSpecBySchoolIdAndTypeIdAndLevelIdService findSpecBySchoolIdAndTypeIdAndLevelIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam(value = "schoolId", required = false)Long schoolId,
                       @RequestParam(value = "typeId", required = false)Long typeId,
                       @RequestParam(value = "levelId", required = false)Long levelId)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        if(null != schoolId){
            request.setAttribute("typeList", findRecruitTypeBySchoolIdService.find(schoolId));
        }
        if(null != schoolId && null != typeId){
            request.setAttribute("levelList", findLevelBySchoolIdAndTypeIdService.find(schoolId, typeId));
        }
        if(null != schoolId && null != typeId && null != levelId){
            request.setAttribute("specList", findSpecBySchoolIdAndTypeIdAndLevelIdService.find(schoolId, typeId, levelId));
        }
        return "eduadmin/teachplan/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, TeachPlan teachPlan) throws Exception {
        return addTeachPlanService.add(request, teachPlan);
    }
}
