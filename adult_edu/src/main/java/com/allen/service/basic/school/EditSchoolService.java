package com.allen.service.basic.school;

import com.allen.entity.basic.School;

/**
 * Created by Allen on 2017/6/28.
 */
public interface EditSchoolService {
    public void edit(School school, String operator)throws Exception;
}
