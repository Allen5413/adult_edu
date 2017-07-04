package com.allen.service.user.user.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.user.user.UserDao;
import com.allen.dao.user.usergroupuser.UserGroupUserDao;
import com.allen.entity.user.User;
import com.allen.entity.user.UserGroupUser;
import com.allen.service.user.user.EditUserService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditUserServiceImpl implements EditUserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserGroupUserDao userGroupUserDao;

    @Override
    @Transactional
    public void edit(User user, long userGroupId, String operator) throws Exception {
        User user2 = userDao.findByLoginName(user.getPhone());
        if(null != user2 && user2.getId() != user.getId()){
            throw new BusinessException("手机号码已存在！");
        }
        if(null == user2){
            user2 = userDao.findOne(user.getId());
        }
        user2.setLoginName(user.getPhone());
        user2.setName(user.getName());
        user2.setPhone(user.getPhone());
        user2.setOperator(operator);
        user2.setOperateTime(DateUtil.getLongNowTime());
        userDao.save(user2);

        UserGroupUser userGroupUser = userGroupUserDao.findByUserId(user2.getId());
        userGroupUser.setUserGroupId(userGroupId);
        userGroupUserDao.save(userGroupUser);
    }
}
