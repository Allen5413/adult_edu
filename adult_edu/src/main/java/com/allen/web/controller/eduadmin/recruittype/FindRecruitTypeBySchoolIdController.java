package com.allen.web.controller.eduadmin.recruittype;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeBySchoolIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/15 0015.
 */
@RestController
public class FindRecruitTypeBySchoolIdController extends BaseController {

    @Autowired
    private FindRecruitTypeBySchoolIdService findRecruitTypeBySchoolIdService;

    @RequestMapping(value = "/findRecruitTypeBySchoolId")
    public JSONObject find(long schoolId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("typeList", findRecruitTypeBySchoolIdService.find(schoolId));
        return jsonObject;
    }
}
