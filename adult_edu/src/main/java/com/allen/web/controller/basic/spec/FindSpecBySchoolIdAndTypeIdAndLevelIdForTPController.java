package com.allen.web.controller.basic.spec;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/7.
 */
@RestController
public class FindSpecBySchoolIdAndTypeIdAndLevelIdForTPController extends BaseController {

    @Autowired
    private FindSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService findSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService;

    @RequestMapping(value = "findSpecBySchoolIdAndTypeIdAndLevelIdForTP")
    public JSONObject find(long schoolId, long typeId, long levelId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("specList", findSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService.find(schoolId, typeId, levelId));
        return jsonObject;
    }
}
