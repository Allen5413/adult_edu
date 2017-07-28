package com.allen.service.datachange;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/3.
 */
public interface AuditDataChangeService {
    public void audit(HttpServletRequest request, long id, int state, String refuseContent)throws Exception;
}
