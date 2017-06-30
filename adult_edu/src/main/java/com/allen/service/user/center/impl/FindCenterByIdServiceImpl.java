package com.allen.service.user.center.impl;

import com.allen.dao.user.center.CenterDao;
import com.allen.entity.user.Center;
import com.allen.service.user.center.FindCenterByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/6/29.
 */
@Service
public class FindCenterByIdServiceImpl implements FindCenterByIdService {

    @Autowired
    private CenterDao centerDao;

    @Override
    public Center find(long id) throws Exception {
        return centerDao.findOne(id);
    }
}
