package com.allen.dao.user.usergroupuser;

import com.allen.entity.user.UserGroupUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2016/12/15.
 */
public interface UserGroupUserDao extends CrudRepository<UserGroupUser, Long> {
    public UserGroupUser findByUserId(long userId)throws Exception;
}
