package com.allen.service.basic.schooltypelevelspec.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.basic.schooltypelevelspec.FindSchoolTypeLevelSpecDao;
import com.allen.service.basic.schooltypelevelspec.PageSchoolTypeLevelSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class PageSchoolTypeLevelSpecServiceImpl implements PageSchoolTypeLevelSpecService{

    @Autowired
    private FindSchoolTypeLevelSpecDao findSchoolTypeLevelSpecDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, String> paramsMap) throws Exception {
        return findSchoolTypeLevelSpecDao.findPage(pageInfo, paramsMap);
    }
}
