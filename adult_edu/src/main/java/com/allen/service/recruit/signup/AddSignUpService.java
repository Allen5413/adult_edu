package com.allen.service.recruit.signup;

import com.allen.entity.recruit.SignUp;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/19.
 */
public interface AddSignUpService {
    public void add(HttpServletRequest request, SignUp signUp)throws Exception;
}
