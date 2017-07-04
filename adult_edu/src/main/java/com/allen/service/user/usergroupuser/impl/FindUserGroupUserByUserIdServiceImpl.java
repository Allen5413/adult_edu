package com.allen.service.user.usergroupuser.impl;

import com.allen.dao.user.usergroupuser.UserGroupUserDao;
import com.allen.entity.user.UserGroupUser;
import com.allen.service.user.usergroupuser.FindUserGroupUserByUserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/4.
 */
@Service
public class FindUserGroupUserByUserIdServiceImpl implements FindUserGroupUserByUserIdService {
    @Autowired
    private UserGroupUserDao userGroupUserDao;
    @Override
    public UserGroupUser find(long userId) throws Exception {
        return userGroupUserDao.findByUserId(userId);
    }
}
