package com.allen.service.basic.course;

import com.allen.entity.basic.Course;

/**
 * Created by Allen on 2017/7/7.
 */
public interface EditCourseService {
    public void edit(Course course, long centerId, int isOperateAudit, long loginId, String editReson)throws Exception;
}
