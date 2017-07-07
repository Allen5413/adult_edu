package com.allen.service.basic.school.impl;

import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class FindSchoolByCenterIdServiceImpl implements FindSchoolByCenterIdService{

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public List<School> find(long centerId) throws Exception {
        return schoolDao.findByCenterId(centerId);
    }
}
