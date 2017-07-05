package com.allen.service.user.user;

import com.allen.entity.user.User;

import java.util.List;

/**
 * Created by Allen on 2017/7/5.
 */
public interface FindUserByCenterIdAndUgIdService {
    public List<User> find(long centerId, long userGroupId)throws Exception;
}
