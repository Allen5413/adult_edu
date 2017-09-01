package com.allen.web.controller.eduadmin.teachplan;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.eduadmin.teachplan.FindTeachPlanBySchoolIdAndSpecIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/21.
 */
@RestController
public class FindTeachPlanBySchoolIdAndSpecIdController extends BaseController {

    @Autowired
    private FindTeachPlanBySchoolIdAndSpecIdService findTeachPlanBySchoolIdAndSpecIdService;

    @RequestMapping(value = "findTeachPlanBySchoolIdAndSpecIdService")
    public JSONObject find(long schoolId, long specId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("tpList", findTeachPlanBySchoolIdAndSpecIdService.find(schoolId, specId));
        return jsonObject;
    }
}
