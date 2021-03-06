package com.allen.web.controller.basic.schooltypelevelspec;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.service.basic.level.FindLevelByCenterIdService;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.schooltypelevelspec.AddSchoolTypeLevelSpecService;
import com.allen.service.basic.schooltypelevelspec.FindSchoolTypeLevelSpecByIdService;
import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByCenterIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
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
@RequestMapping(value = "/addSchoolTypeLevelSpec")
public class AddSchoolTypeLevelSpecController extends BaseController {

    @Autowired
    private AddSchoolTypeLevelSpecService addSchoolTypeLevelSpecService;
    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;
    @Autowired
    private FindRecruitTypeByCenterIdService findRecruitTypeByCenterIdService;
    @Autowired
    private FindLevelByCenterIdService findLevelByCenterIdService;
    @Autowired
    private FindSchoolByIdService findSchoolByIdService;
    @Autowired
    private FindRecruitTypeByIdService findRecruitTypeByIdService;
    @Autowired
    private FindLevelByIdService findLevelByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("typeList", findRecruitTypeByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("levelList", findLevelByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        return "basic/schooltypelevelspec/add";
    }

    /**
     * @return
     */
    @RequestMapping(value = "openFromDetail")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams,
                       long schoolId, long typeId, long levelId)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("school", findSchoolByIdService.find(schoolId));
        request.setAttribute("type", findRecruitTypeByIdService.find(typeId));
        request.setAttribute("level", findLevelByIdService.find(levelId));
        return "basic/schooltypelevelspec/addFromDetail";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,
                          SchoolTypeLevelSpec schoolTypeLevelSpec,
                          String specCode, String specName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != schoolTypeLevelSpec) {
            schoolTypeLevelSpec.setOperator(UserUtil.getLoginUserForName(request));
            addSchoolTypeLevelSpecService.add(schoolTypeLevelSpec, specCode, specName);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
