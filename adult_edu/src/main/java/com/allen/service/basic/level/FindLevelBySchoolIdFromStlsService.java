package com.allen.service.basic.level;

import com.allen.entity.basic.Level;

import java.util.List;

/**
 * Created by Allen on 2017/7/15 0015.
 */
public interface FindLevelBySchoolIdFromStlsService {
    public List<Level> find(long schoolId)throws Exception;
}
