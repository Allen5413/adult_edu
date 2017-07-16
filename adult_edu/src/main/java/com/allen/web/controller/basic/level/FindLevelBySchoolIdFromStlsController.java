package com.allen.web.controller.basic.level;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.level.FindLevelBySchoolIdFromStlsService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/10.
 */
@RestController
public class FindLevelBySchoolIdFromStlsController extends BaseController {

    @Autowired
    private FindLevelBySchoolIdFromStlsService findLevelBySchoolIdFromStlsService;

    @RequestMapping(value = "/findLevelBySchoolIdFromStls")
    public JSONObject del(long schoolId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("levelList", findLevelBySchoolIdFromStlsService.find(schoolId));
        return jsonObject;
    }
}
