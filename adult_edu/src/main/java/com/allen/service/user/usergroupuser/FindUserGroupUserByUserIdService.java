package com.allen.service.user.usergroupuser;

import com.allen.entity.user.UserGroupUser;

/**
 * Created by Allen on 2017/7/4.
 */
public interface FindUserGroupUserByUserIdService {
    public UserGroupUser find(long userId)throws Exception;
}
