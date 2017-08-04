package com.allen.service.eduadmin.studentcourse.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.eduadmin.studentcourse.FindStudentCourseDao;
import com.allen.service.eduadmin.studentcourse.PageStudentCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageStudentCourseServiceImpl implements PageStudentCourseService {

    @Resource
    private FindStudentCourseDao findStudentCourseDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, String> params) throws Exception {
        return findStudentCourseDao.findPage(pageInfo, params);
    }
}
