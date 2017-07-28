package com.allen.service.recruit.signup;

import com.allen.entity.recruit.SignUp;

/**
 * Created by Allen on 2017/7/25 0025.
 */
public interface FindSignUpByIdService {
    public SignUp find(long id)throws Exception;
}
