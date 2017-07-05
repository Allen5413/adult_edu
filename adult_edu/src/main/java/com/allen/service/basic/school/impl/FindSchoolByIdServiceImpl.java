package com.allen.service.basic.school.impl;

import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.FindSchoolByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/5.
 */
@Service
public class FindSchoolByIdServiceImpl implements FindSchoolByIdService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public School find(long id) throws Exception {
        return schoolDao.findOne(id);
    }
}
