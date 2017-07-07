package com.allen.service.basic.spec;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/7.
 */
public interface FindSpecBySchoolIdAndTypeIdAndLevelIdService {
    public List<Map> find(long schoolId, long typeId, long levelId)throws Exception;
}
