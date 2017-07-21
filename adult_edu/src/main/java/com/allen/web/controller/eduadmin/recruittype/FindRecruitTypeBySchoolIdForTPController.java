package com.allen.web.controller.eduadmin.recruittype;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeBySchoolIdForTeachPlanService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/15 0015.
 */
@RestController
public class FindRecruitTypeBySchoolIdForTPController extends BaseController {

    @Autowired
    private FindRecruitTypeBySchoolIdForTeachPlanService findRecruitTypeBySchoolIdForTeachPlanService;

    @RequestMapping(value = "/findRecruitTypeBySchoolIdForTP")
    public JSONObject find(long schoolId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("typeList", findRecruitTypeBySchoolIdForTeachPlanService.find(schoolId));
        return jsonObject;
    }
}
