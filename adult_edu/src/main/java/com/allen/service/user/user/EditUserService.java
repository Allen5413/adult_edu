package com.allen.service.user.user;

import com.allen.entity.user.User;

/**
 * Created by Allen on 2017/6/28.
 */
public interface EditUserService {
    public void edit(User user, Long userGroupId, String operator)throws Exception;
}
