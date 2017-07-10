package com.allen.service.basic.spec.impl;

import com.allen.dao.basic.spec.SpecDao;
import com.allen.entity.basic.Spec;
import com.allen.service.basic.spec.FindSpecByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/10.
 */
@Service
public class FindSpecByIdServiceImpl implements FindSpecByIdService {
    @Autowired
    private SpecDao specDao;

    @Override
    public Spec find(long id) throws Exception {
        return specDao.findOne(id);
    }
}
