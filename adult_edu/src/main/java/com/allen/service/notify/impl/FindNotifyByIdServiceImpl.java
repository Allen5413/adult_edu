package com.allen.service.notify.impl;

import com.allen.dao.notify.NotifyDao;
import com.allen.entity.notify.Notify;
import com.allen.service.notify.FindNotifyByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/9/11.
 */
@Service
public class FindNotifyByIdServiceImpl implements FindNotifyByIdService {

    @Autowired
    private NotifyDao notifyDao;

    @Override
    public Notify find(long id) throws Exception {
        return notifyDao.findOne(id);
    }
}
