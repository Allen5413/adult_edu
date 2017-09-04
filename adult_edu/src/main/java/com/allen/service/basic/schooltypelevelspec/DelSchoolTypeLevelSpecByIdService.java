package com.allen.service.basic.schooltypelevelspec;

/**
 * Created by Allen on 2017/7/10.
 */
public interface DelSchoolTypeLevelSpecByIdService {
    public void del(long id, long specId, long centerId, int isAudit, long operateId, String editReson)throws Exception;
}
