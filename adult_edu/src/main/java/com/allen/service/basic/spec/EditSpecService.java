package com.allen.service.basic.spec;

import com.allen.entity.basic.Spec;

/**
 * Created by Allen on 2017/7/7.
 */
public interface EditSpecService {
    public void edit(Spec spec, long centerId, int isOperateAudit, long loginId, String editReson)throws Exception;
}
