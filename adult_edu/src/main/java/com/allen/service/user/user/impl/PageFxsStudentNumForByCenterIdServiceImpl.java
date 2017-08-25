package com.allen.service.user.user.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.user.user.FindUserDao;
import com.allen.service.user.user.PageFxsStudentNumForByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/8/25.
 */
@Service
public class PageFxsStudentNumForByCenterIdServiceImpl implements PageFxsStudentNumForByCenterIdService {

    @Autowired
    private FindUserDao findUserDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap) throws Exception {
        return findUserDao.findFxsStudentNumForByCenterIdPage(pageInfo, paramsMap, sortMap);
    }
}
