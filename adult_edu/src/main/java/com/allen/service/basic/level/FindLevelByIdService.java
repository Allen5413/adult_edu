package com.allen.service.basic.level;

import com.allen.entity.basic.Level;

/**
 * Created by Allen on 2017/7/6.
 */
public interface FindLevelByIdService {
    public Level find(long id)throws Exception;
}
