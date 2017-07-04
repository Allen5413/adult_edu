package com.allen.service.user.user;

import com.allen.entity.user.User;

/**
 * Created by Allen on 2017/6/29.
 */
public interface FindUserByIdService {
    public User find(long id)throws Exception;
}
