package com.allen.service.user.user;

import com.allen.entity.user.User;

import java.util.List;

/**
 * Created by Allen on 2017/7/3.
 */
public interface FindUserByCenterIdService {
    public List<User> find(long centerId)throws Exception;
}
