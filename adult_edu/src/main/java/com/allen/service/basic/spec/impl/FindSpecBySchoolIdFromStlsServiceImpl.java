package com.allen.service.basic.spec.impl;

import com.allen.dao.basic.spec.SpecDao;
import com.allen.entity.basic.Spec;
import com.allen.service.basic.spec.FindSpecBySchoolIdFromStlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/15 0015.
 */
@Service
public class FindSpecBySchoolIdFromStlsServiceImpl implements FindSpecBySchoolIdFromStlsService {

    @Autowired
    private SpecDao specDao;

    @Override
    public List<Spec> find(long schoolId) throws Exception {
        return specDao.findBySchoolFromStls(schoolId);
    }
}
