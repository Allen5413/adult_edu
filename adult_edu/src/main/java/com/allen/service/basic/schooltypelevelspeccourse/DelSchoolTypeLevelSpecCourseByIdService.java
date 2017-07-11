package com.allen.service.basic.schooltypelevelspeccourse;

/**
 * Created by Allen on 2017/7/10.
 */
public interface DelSchoolTypeLevelSpecCourseByIdService {
    public void del(long id, long centerId, int isAudit, long operateId, String editReson)throws Exception;
}
