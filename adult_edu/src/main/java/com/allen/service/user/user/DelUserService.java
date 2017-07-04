package com.allen.service.user.user;

import com.allen.entity.user.User;

/**
 * Created by Allen on 2017/6/29.
 */
public interface DelUserService {
    public void del(long id, String loginName)throws Exception;
}
