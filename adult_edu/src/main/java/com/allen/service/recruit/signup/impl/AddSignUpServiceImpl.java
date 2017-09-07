package com.allen.service.recruit.signup.impl;

import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.entity.recruit.SignUp;
import com.allen.service.recruit.signup.AddSignUpService;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/19.
 */
@Service
public class AddSignUpServiceImpl implements AddSignUpService {

    @Autowired
    private SignUpDao signUpDao;
    @Autowired
    private ConfigProp configProp;

    @Override
    @Transactional
    public void add(HttpServletRequest request, SignUp signUp) throws Exception {
        System.out.println("signUp.getCenterId():   "+signUp.getCenterId());
        SignUp signUp2 = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndIdCard(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getTeachPlanId(), signUp.getIdCard());
        if(null != signUp2 && !StringUtil.isEmpty(signUp2.getName())){
            throw new BusinessException("身份证号码在同一个学校、招生类型、层次、专业、批次下已存在！");
        }
        signUp2 = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndPhone(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getTeachPlanId(), signUp.getPhone());
        if(null != signUp2 && !StringUtil.isEmpty(signUp2.getName())){
            throw new BusinessException("手机号码在同一个学校、招生类型、层次、专业、批次下已存在！");
        }

        String photoUrl = signUp.getPhotoUrl();
        String idCardFrontUrl = signUp.getIdCardFrontUrl();
        String idCardBackUrl = signUp.getIdCardBackUrl();
        String diplomaUrl = signUp.getDiplomaUrl();
        String xxwUrl = signUp.getXxwUrl();
        String ydsUrl = signUp.getYdsUrl();
        if(StringUtil.isEmpty(photoUrl)){
            throw new BusinessException("请上传照片");
        }
        if(StringUtil.isEmpty(idCardFrontUrl)){
            throw new BusinessException("请上传身份证正面照");
        }
        if(StringUtil.isEmpty(idCardBackUrl)){
            throw new BusinessException("请上传身份证背面照");
        }
        if(StringUtil.isEmpty(diplomaUrl)){
            throw new BusinessException("请上传学历证书照");
        }

        signUpDao.save(signUp);

        //把上传的照片从临时目录剪切到正式目录，并把文件名改成id
        UpLoadFileUtil.custFile(request, photoUrl, configProp.getSignUp().get("photoUrl"), signUp.getId()+".png");
        UpLoadFileUtil.custFile(request, idCardFrontUrl, configProp.getSignUp().get("idCardFrontUrl"), signUp.getId()+".png");
        UpLoadFileUtil.custFile(request, idCardBackUrl, configProp.getSignUp().get("idCardBackUrl"), signUp.getId()+".png");
        UpLoadFileUtil.custFile(request, diplomaUrl, configProp.getSignUp().get("diplomaUrl"), signUp.getId()+".png");

        signUp.setPhotoUrl(configProp.getDomain().get("xiwang")+configProp.getSignUp().get("photoUrl")+signUp.getId()+".png");
        signUp.setIdCardFrontUrl(configProp.getDomain().get("xiwang")+configProp.getSignUp().get("idCardFrontUrl") + signUp.getId() + ".png");
        signUp.setIdCardBackUrl(configProp.getDomain().get("xiwang")+configProp.getSignUp().get("idCardBackUrl") + signUp.getId() + ".png");
        signUp.setDiplomaUrl(configProp.getDomain().get("xiwang")+configProp.getSignUp().get("diplomaUrl") + signUp.getId() + ".png");

//        signUp.setPhotoUrl(configProp.getDomain().get("localhost")+configProp.getSignUp().get("photoUrl")+signUp.getId()+".png");
//        signUp.setIdCardFrontUrl(configProp.getDomain().get("localhost")+configProp.getSignUp().get("idCardFrontUrl") + signUp.getId() + ".png");
//        signUp.setIdCardBackUrl(configProp.getDomain().get("localhost")+configProp.getSignUp().get("idCardBackUrl") + signUp.getId() + ".png");
//        signUp.setDiplomaUrl(configProp.getDomain().get("localhost")+configProp.getSignUp().get("diplomaUrl") + signUp.getId() + ".png");

        if(!StringUtil.isEmpty(xxwUrl)){
            UpLoadFileUtil.custFile(request, xxwUrl, configProp.getSignUp().get("xxwUrl"), signUp.getId()+".png");
            signUp.setXxwUrl(configProp.getDomain().get("xiwang")+configProp.getSignUp().get("xxwUrl") + signUp.getId() + ".png");
        }
        if(!StringUtil.isEmpty(ydsUrl)){
            UpLoadFileUtil.custFile(request, ydsUrl, configProp.getSignUp().get("ydsUrl"), signUp.getId()+".png");
            signUp.setYdsUrl(configProp.getDomain().get("xiwang")+configProp.getSignUp().get("ydsUrl") + signUp.getId() + ".png");
        }
        signUpDao.save(signUp);
    }
}
