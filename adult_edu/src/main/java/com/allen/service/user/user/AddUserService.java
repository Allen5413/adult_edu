package com.allen.service.user.user;

import com.allen.entity.user.User;

/**
 * Created by Allen on 2017/6/29.
 */
public interface AddUserService {
    public void add(User user, long userGroupId)throws Exception;
}
