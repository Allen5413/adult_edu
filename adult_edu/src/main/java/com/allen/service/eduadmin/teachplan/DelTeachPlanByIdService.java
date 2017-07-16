package com.allen.service.eduadmin.teachplan;

/**
 * Created by Allen on 2017/6/28.
 */
public interface DelTeachPlanByIdService {
    public void del(long id, long centerId, int isAudit, long operateId, String editReson)throws Exception;
}
