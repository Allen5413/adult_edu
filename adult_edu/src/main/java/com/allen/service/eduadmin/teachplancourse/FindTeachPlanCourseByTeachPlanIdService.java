package com.allen.service.eduadmin.teachplancourse;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/16 0016.
 */
public interface FindTeachPlanCourseByTeachPlanIdService {
    public List<Map> find(Long teachPlanId)throws Exception;
}
