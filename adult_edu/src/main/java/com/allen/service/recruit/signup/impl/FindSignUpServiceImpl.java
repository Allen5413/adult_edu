package com.allen.service.recruit.signup.impl;

import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.entity.recruit.SignUp;
import com.allen.service.recruit.signup.FindSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/25 0025.
 */
@Service
public class FindSignUpServiceImpl implements FindSignUpService {

    @Autowired
    private SignUpDao signUpDao;


    @Override
    public SignUp find(long id) throws Exception {
        return signUpDao.findOne(id);
    }
}
