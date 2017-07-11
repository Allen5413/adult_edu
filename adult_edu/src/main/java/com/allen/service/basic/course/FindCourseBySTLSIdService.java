package com.allen.service.basic.course;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/7.
 */
public interface FindCourseBySTLSIdService {
    public List<Map> find(long schoolTypeLevelSpecId)throws Exception;
}
