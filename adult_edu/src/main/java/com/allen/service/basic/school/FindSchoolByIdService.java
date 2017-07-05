package com.allen.service.basic.school;

import com.allen.entity.basic.School;

/**
 * Created by Allen on 2017/7/5.
 */
public interface FindSchoolByIdService {
    public School find(long id)throws Exception;
}
