package com.allen.web.controller.basic.level;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.level.FindLevelBySchoolIdAndTypeIdForTeachPlanService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/15 0015.
 */
@RestController
public class FindLevelBySchoolIdAndTypeIdForTPController extends BaseController {

    @Autowired
    private FindLevelBySchoolIdAndTypeIdForTeachPlanService findLevelBySchoolIdAndTypeIdForTeachPlanService;

    @RequestMapping(value = "/findLevelBySchoolIdAndTypeIdForTP")
    public JSONObject find(long schoolId, long typeId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("levelList", findLevelBySchoolIdAndTypeIdForTeachPlanService.find(schoolId, typeId));
        return jsonObject;
    }
}
