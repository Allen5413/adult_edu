package com.allen.service.basic.level;

import com.allen.entity.basic.Level;

import java.util.List;

/**
 * Created by Allen on 2017/7/6.
 */
public interface FindLevelByCenterIdService {
    public List<Level> find(long centerId)throws Exception;
}
