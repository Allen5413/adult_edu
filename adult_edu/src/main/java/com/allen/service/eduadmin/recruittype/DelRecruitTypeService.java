package com.allen.service.eduadmin.recruittype;

/**
 * Created by Allen on 2017/6/28.
 */
public interface DelRecruitTypeService {
    public void del(long id, long centerId, int isAudit, long operateId, String editReson)throws Exception;
}
