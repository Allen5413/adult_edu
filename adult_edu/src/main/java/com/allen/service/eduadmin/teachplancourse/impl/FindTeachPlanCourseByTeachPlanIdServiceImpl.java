package com.allen.service.eduadmin.teachplancourse.impl;

import com.allen.dao.eduadmin.teachplancourse.FindTeachPlanCourseDao;
import com.allen.service.eduadmin.teachplancourse.FindTeachPlanCourseByTeachPlanIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/16 0016.
 */
@Service
public class FindTeachPlanCourseByTeachPlanIdServiceImpl implements FindTeachPlanCourseByTeachPlanIdService {

    @Autowired
    private FindTeachPlanCourseDao findTeachPlanCourseDao;

    @Override
    public List<Map> find(Long teachPlanId) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tpc.teach_plan_id", teachPlanId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("c.code", true);
        return findTeachPlanCourseDao.findByTeachPlanId(paramMap, sortMap);
    }
}
