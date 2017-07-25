package com.allen.service.user.user;

import com.allen.entity.user.User;

import java.util.List;

/**
 * Created by Allen on 2017/7/25 0025.
 */
public interface FindUserByCenterIdAndTypeService {
    public List<User> find(long centerId, int type)throws Exception;
}
