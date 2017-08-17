package com.allen.service.basic.school;

import com.allen.entity.basic.School;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
public interface AddSchoolService {
    public void add(HttpServletRequest request, School school)throws Exception;
}
