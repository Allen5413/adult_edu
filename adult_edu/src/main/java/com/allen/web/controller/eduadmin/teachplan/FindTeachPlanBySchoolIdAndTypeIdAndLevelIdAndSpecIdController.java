package com.allen.web.controller.eduadmin.teachplan;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.eduadmin.teachplan.FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/21.
 */
@RestController
public class FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdController extends BaseController {

    @Autowired
    private FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService findTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService;

    @RequestMapping(value = "findTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecId")
    public JSONObject find(long schoolId, long typeId, long levelId, long specId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("tpList", findTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService.find(schoolId, typeId, levelId, specId));
        return jsonObject;
    }
}
