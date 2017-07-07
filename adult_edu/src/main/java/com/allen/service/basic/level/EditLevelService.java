package com.allen.service.basic.level;

import com.allen.entity.basic.Level;

/**
 * Created by Allen on 2017/6/28.
 */
public interface EditLevelService {
    public void edit(Level level, long centerId, int isAudit, long operateId, String editReson)throws Exception;
}
