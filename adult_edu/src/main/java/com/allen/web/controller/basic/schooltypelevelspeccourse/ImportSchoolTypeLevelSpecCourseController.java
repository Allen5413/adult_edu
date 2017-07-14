package com.allen.web.controller.basic.schooltypelevelspeccourse;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.schooltypelevelspeccourse.ImportSchoolTypeLevelSpecCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
@RestController
public class ImportSchoolTypeLevelSpecCourseController {

    @Autowired
    private ImportSchoolTypeLevelSpecCourseService importSchoolTypeLevelSpecCourseService;

    @RequestMapping(value = "/importStlsc")
    public JSONObject importCourse(HttpServletRequest request, long stlsId)throws Exception{
        JSONObject jsonObject = importSchoolTypeLevelSpecCourseService.importCourse(request, stlsId);
        return jsonObject;
    }
}
