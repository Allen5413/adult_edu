package com.allen.service.notify.impl;

import com.allen.dao.notify.NotifyDao;
import com.allen.service.notify.DelNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/9/12.
 */
@Service
public class DelNotifyServiceImpl implements DelNotifyService {

    @Autowired
    private NotifyDao notifyDao;

    @Override
    public void del(long id) throws Exception {
        notifyDao.delete(id);
    }
}
