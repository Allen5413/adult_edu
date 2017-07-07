package com.allen.service.basic.school;

import com.allen.entity.basic.School;

import java.util.List;

/**
 * Created by Allen on 2017/7/7.
 */
public interface FindSchoolByCenterIdService {
    public List<School> find(long centerId)throws Exception;
}
