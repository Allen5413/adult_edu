package com.allen.service.recruit.signup.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.entity.recruit.SignUp;
import com.allen.service.recruit.signup.AddSignUpService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/19.
 */
@Service
public class AddSignUpServiceImpl implements AddSignUpService {

    @Autowired
    private SignUpDao signUpDao;

    @Override
    public void add(SignUp signUp) throws Exception {
        SignUp signUp2 = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndIdCard(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getIdCard());
        if(null != signUp2 && !StringUtil.isEmpty(signUp2.getName())){
            throw new BusinessException("身份证号码在同一个学校、招生类型、层次、专业下已存在！");
        }
        signUp2 = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndPhone(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getPhone());
        if(null != signUp2 && !StringUtil.isEmpty(signUp2.getName())){
            throw new BusinessException("手机号码在同一个学校、招生类型、层次、专业下已存在！");
        }
        signUpDao.save(signUp);
    }
}
