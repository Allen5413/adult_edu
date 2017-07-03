package com.allen.service.datachange;

/**
 * Created by Allen on 2017/7/3.
 */
public interface AuditDataChangeService {
    public void audit(long id, int state, String refuseContent)throws Exception;
}
