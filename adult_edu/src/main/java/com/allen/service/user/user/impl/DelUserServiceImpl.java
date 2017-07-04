package com.allen.service.user.user.impl;

import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.user.user.DelUserService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class DelUserServiceImpl implements DelUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void del(long id, String loginName) throws Exception {
        User user = userDao.findOne(id);
        user.setState(User.STATE_DELETE);
        user.setOperator(loginName);
        user.setOperateTime(DateUtil.getLongNowTime());
        userDao.save(user);
    }
}
