package com.allen.service.basic.spec.impl;

import com.allen.dao.basic.spec.SpecDao;
import com.allen.service.basic.spec.FindSpecForNameByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/8/23.
 */
@Service
public class FindSpecForNameByCenterIdServiceImpl implements FindSpecForNameByCenterIdService {

    @Autowired
    private SpecDao specDao;

    @Override
    public List<String> find(long centerId) throws Exception {
        return specDao.findForNameByCenterId(centerId);
    }
}
