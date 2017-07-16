package com.allen.web.controller.basic.level;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.level.FindLevelBySchoolIdAndTypeIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/15 0015.
 */
@RestController
public class FindLevelBySchoolIdAndTypeIdController extends BaseController {

    @Autowired
    private FindLevelBySchoolIdAndTypeIdService findLevelBySchoolIdAndTypeIdService;

    @RequestMapping(value = "/findLevelBySchoolIdAndTypeId")
    public JSONObject find(long schoolId, long typeId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("levelList", findLevelBySchoolIdAndTypeIdService.find(schoolId, typeId));
        return jsonObject;
    }
}
