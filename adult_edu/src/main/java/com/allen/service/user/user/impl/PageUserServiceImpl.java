package com.allen.service.user.user.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.user.user.FindUserDao;
import com.allen.service.user.user.PageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/7/4.
 */
@Service
public class PageUserServiceImpl implements PageUserService {

    @Autowired
    private FindUserDao findUserDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, String> params) throws Exception {
        return findUserDao.findPage(pageInfo, params);
    }
}
