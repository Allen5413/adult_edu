package com.allen.service.notify.impl;

import com.allen.dao.notify.NotifyDao;
import com.allen.entity.notify.Notify;
import com.allen.service.notify.AddNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/8/30.
 */
@Service
public class AddNotifyServiceImpl implements AddNotifyService {

    @Autowired
    private NotifyDao notifyDao;

    @Override
    public void add(Notify notify) throws Exception {
        notifyDao.save(notify);
    }
}
