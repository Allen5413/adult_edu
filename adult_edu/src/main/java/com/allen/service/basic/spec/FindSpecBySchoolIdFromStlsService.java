package com.allen.service.basic.spec;

import com.allen.entity.basic.Spec;

import java.util.List;

/**
 * Created by Allen on 2017/7/15 0015.
 */
public interface FindSpecBySchoolIdFromStlsService {
    public List<Spec> find(long schoolId)throws Exception;
}
