package com.allen.web.controller.basic.schooltypelevelspec;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.service.basic.level.FindLevelByCenterIdService;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.basic.schooltypelevelspec.AddSchoolTypeLevelSpecService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByCenterIdService;
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
