package com.allen.web.controller.basic.spec;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.spec.FindSpecBySchoolIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/15 0015.
 */
@RestController
public class FindSpecBySchoolIdController extends BaseController {

    @Autowired
    private FindSpecBySchoolIdService findSpecBySchoolIdService;

    @RequestMapping(value = "/findSpecBySchoolId")
    public JSONObject del(long schoolId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("specList", findSpecBySchoolIdService.find(schoolId));
        return jsonObject;
    }
}
