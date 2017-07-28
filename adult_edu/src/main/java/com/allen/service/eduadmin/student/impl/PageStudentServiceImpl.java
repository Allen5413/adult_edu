package com.allen.service.eduadmin.student.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.eduadmin.student.FindStudentDao;
import com.allen.service.eduadmin.student.PageStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageStudentServiceImpl implements PageStudentService {

    @Resource
    private FindStudentDao findStudentDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findStudentDao.findPage(pageInfo, params, sortMap);
    }
}
