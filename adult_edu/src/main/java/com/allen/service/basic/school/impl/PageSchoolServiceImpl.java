package com.allen.service.basic.school.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.basic.school.FindSchoolDao;
import com.allen.service.basic.school.PageSchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageSchoolServiceImpl implements PageSchoolService {

    @Resource
    private FindSchoolDao findSchoolDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findSchoolDao.findPage(pageInfo, params, sortMap);
    }
}
