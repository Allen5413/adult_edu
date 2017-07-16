package com.allen.service.basic.level;

import com.allen.entity.basic.Level;

import java.util.List;

/**
 * Created by Allen on 2017/7/15 0015.
 */
public interface FindLevelBySchoolIdAndTypeIdService {
    public List<Level> find(long schoolId, long typeId)throws Exception;
}
