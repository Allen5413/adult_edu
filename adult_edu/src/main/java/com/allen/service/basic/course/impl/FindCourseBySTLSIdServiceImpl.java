package com.allen.service.basic.course.impl;

import com.allen.dao.basic.course.FindCourseDao;
import com.allen.service.basic.course.FindCourseBySTLSIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/11.
 */
@Service
public class FindCourseBySTLSIdServiceImpl implements FindCourseBySTLSIdService {

    @Autowired
    private FindCourseDao findCourseDao;

    @Override
    public List<Map> find(long schoolTypeLevelSpecId) throws Exception {
        return findCourseDao.findBySTLSId(schoolTypeLevelSpecId);
    }
}
