package com.allen.service.basic.spec;

import com.allen.entity.basic.Spec;

import java.util.List;

/**
 * Created by Allen on 2017/8/31.
 */
public interface FindSpecBySchoolIdService {
    public List<Spec> find(long schoolId)throws Exception;
}
