package com.allen.service.recruit.signup;

import com.allen.entity.recruit.SignUp;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
public interface EditSignUpService {
    public void edit(HttpServletRequest request, SignUp signUp, long centerId, int isAudit, long operateId, String editReson, int isTimeOut)throws Exception;
}
