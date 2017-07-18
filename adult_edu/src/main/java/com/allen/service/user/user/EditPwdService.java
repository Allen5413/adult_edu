package com.allen.service.user.user;

/**
 * Created by Allen on 2017/7/18.
 */
public interface EditPwdService {
    public void edit(String oldPwd, String newPwd, long id)throws Exception;
}
