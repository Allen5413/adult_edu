package com.allen.service.notify;

/**
 * Created by Allen on 2017/9/11.
 */
public interface AuditNotifyService {
    public void auditPass(long id, String operator)throws Exception;

    public void auditNot(long id, String refuseRemark, String operator)throws Exception;
}
