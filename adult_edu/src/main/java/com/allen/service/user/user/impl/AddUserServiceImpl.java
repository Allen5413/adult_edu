package com.allen.service.user.user.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.user.user.UserDao;
import com.allen.dao.user.usergroupuser.UserGroupUserDao;
import com.allen.entity.user.User;
import com.allen.entity.user.UserGroupUser;
import com.allen.service.user.user.AddUserService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddUserServiceImpl implements AddUserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserGroupUserDao userGroupUserDao;

    @Override
    @Transactional
    public void add(User user, long userGroupId) throws Exception {
        User user2 = userDao.findByLoginName(user.getLoginName());
        if(null != user2 && !StringUtil.isEmpty(user2.getLoginName())){
            throw new BusinessException("登录名已存在！");
        }
        userDao.save(user);

        UserGroupUser userGroupUser = new UserGroupUser();
        userGroupUser.setUserId(user.getId());
        userGroupUser.setUserGroupId(userGroupId);
        userGroupUser.setCreator(user.getCreator());
        userGroupUserDao.save(userGroupUser);
    }
}
