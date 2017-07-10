package com.allen.web.controller.basic.schooltypelevelspec;

import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.entity.basic.Spec;
import com.allen.entity.datachange.DataChange;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.basic.school.FindSchoolByIdService;
import com.allen.service.basic.schooltypelevelspec.FindSchoolTypeLevelSpecByIdService;
import com.allen.service.basic.spec.FindSpecByIdService;
import com.allen.service.datachange.FindDataChangeByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/findSchoolTypeLevelSpecById")
public class FindSchoolTypeLevelSpecByIdController extends BaseController {

    @Autowired
    private FindDataChangeByIdService findDataChangeByIdService;
    @Autowired
    private FindSchoolTypeLevelSpecByIdService findSchoolTypeLevelSpecByIdService;
    @Autowired
    private FindSchoolByIdService findSchoolByIdService;
    @Autowired
    private FindRecruitTypeByIdService findRecruitTypeByIdService;
    @Autowired
    private FindLevelByIdService findLevelByIdService;
    @Autowired
    private FindSpecByIdService findSpecByIdService;


    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(@RequestParam("id")long id,
                       @RequestParam(value = "dataChangeId", required = false)Long dataChangeId,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       HttpServletRequest request)throws Exception{
        SchoolTypeLevelSpec schoolTypeLevelSpec = findSchoolTypeLevelSpecByIdService.find(id);
        if(null != dataChangeId) {
            DataChange dataChange = findDataChangeByIdService.find(dataChangeId);
            request.setAttribute("dataChange", dataChange);
        }
        request.setAttribute("schoolTypeLevelSpec", schoolTypeLevelSpec);
        request.setAttribute("school", findSchoolByIdService.find(schoolTypeLevelSpec.getSchoolId()));
        request.setAttribute("recruitType", findRecruitTypeByIdService.find(schoolTypeLevelSpec.getRecruitTypeId()));
        request.setAttribute("level", findLevelByIdService.find(schoolTypeLevelSpec.getLevelId()));
        request.setAttribute("spec", findSpecByIdService.find(schoolTypeLevelSpec.getSpecId()));
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "basic/schooltypelevelspec/info";
    }
}
