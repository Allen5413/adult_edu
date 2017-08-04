package com.allen.service.eduadmin.studentcourse;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
public interface PageStudentCourseService {
    public PageInfo find(PageInfo pageInfo, Map<String, String> params)throws Exception;
}
