package com.allen.web.controller.basic.schooltypelevelspec;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.schooltypelevelspec.ImportSchoolTypeLevelSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
@RestController
public class ImportSchoolTypeLevelSpecController {

    @Autowired
    private ImportSchoolTypeLevelSpecService importSchoolTypeLevelSpecService;

    @RequestMapping(value = "/importSchoolTypeLevelSpec")
    public JSONObject importSpec(HttpServletRequest request, long schoolId, long typeId, long levelId)throws Exception{
        JSONObject jsonObject = importSchoolTypeLevelSpecService.importSpec(request, schoolId, typeId, levelId);
        return jsonObject;
    }
}
