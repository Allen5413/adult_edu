package com.allen.service.recruit.signup.impl;

import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.School;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.fee.FeeType;
import com.allen.entity.recruit.SignUp;
import com.allen.entity.user.User;
import com.allen.service.fee.feetype.EditFeeTypeService;
import com.allen.service.recruit.signup.EditSignUpService;
import com.allen.util.DateUtil;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditSignUpServiceImpl implements EditSignUpService {

    @Autowired
    private SignUpDao signUpDao;
    @Autowired
    private ConfigProp configProp;
    @Autowired
    private DataChangeDao dataChangeDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private RecruitTypeDao recruitTypeDao;
    @Autowired
    private LevelDao levelDao;

    @Override
    public void edit(HttpServletRequest request, SignUp signUp, long centerId, int isAudit, long operateId, String editReson) throws Exception {
        SignUp signUp2 = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndIdCard(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getIdCard());
        if(null != signUp2 && signUp2.getId() != signUp.getId()){
            throw new BusinessException("身份证号码在同一个学校、招生类型、层次、专业下已存在！");
        }
        signUp2 = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndPhone(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getPhone());
        if(null != signUp2 && signUp2.getId() != signUp.getId()){
            throw new BusinessException("手机号码在同一个学校、招生类型、层次、专业下已存在！");
        }
        //查询操作是否需要审核
        if(isAudit == User.ISOPERATEAUDIT_NOT) {
            if (null == signUp2) {
                signUp2 = signUpDao.findOne(signUp.getId());
            }
            String photoUrl = signUp.getPhotoUrl();
            String idCardFrontUrl = signUp.getIdCardFrontUrl();
            String idCardBackUrl = signUp.getIdCardBackUrl();
            String diplomaUrl = signUp.getDiplomaUrl();
            String xxwUrl = signUp.getXxwUrl();
            String ydsUrl = signUp.getYdsUrl();
            if(!photoUrl.equals(signUp2.getPhotoUrl())) {
                if (StringUtil.isEmpty(photoUrl)) {
                    throw new BusinessException("请上传照片");
                }else{
                    //把上传的照片从临时目录剪切到正式目录，并把文件名改成id
                    UpLoadFileUtil.custFile(request, photoUrl, configProp.getSignUp().get("photoUrl"), signUp.getId() + ".png");
                }
            }
            if(!idCardFrontUrl.equals(signUp2.getIdCardFrontUrl())) {
                if (StringUtil.isEmpty(idCardFrontUrl)) {
                    throw new BusinessException("请上传身份证正面照");
                }else{
                    UpLoadFileUtil.custFile(request, idCardFrontUrl, configProp.getSignUp().get("idCardFrontUrl"), signUp.getId()+".png");
                }
            }
            if(!idCardBackUrl.equals(signUp2.getIdCardBackUrl())) {
                if (StringUtil.isEmpty(idCardBackUrl)) {
                    throw new BusinessException("请上传身份证背面照");
                }else{
                    UpLoadFileUtil.custFile(request, idCardBackUrl, configProp.getSignUp().get("idCardBackUrl"), signUp.getId()+".png");
                }
            }
            if(!diplomaUrl.equals(signUp2.getDiplomaUrl())) {
                if (StringUtil.isEmpty(diplomaUrl)) {
                    throw new BusinessException("请上传学历证书照");
                }else{
                    UpLoadFileUtil.custFile(request, diplomaUrl, configProp.getSignUp().get("diplomaUrl"), signUp.getId()+".png");
                }
            }
            if(!xxwUrl.equals(signUp2.getXxwUrl())){
                if (StringUtil.isEmpty(xxwUrl)) {
                    signUp.setXxwUrl("");
                }else{
                    UpLoadFileUtil.custFile(request, xxwUrl, configProp.getSignUp().get("xxwUrl"), signUp.getId()+".png");
                    signUp.setXxwUrl(configProp.getSignUp().get("xxwUrl") + signUp.getId() + ".png");
                }
            }
            if(!ydsUrl.equals(signUp2.getYdsUrl())){
                if (StringUtil.isEmpty(ydsUrl)) {
                    signUp.setYdsUrl("");
                }else{
                    UpLoadFileUtil.custFile(request, ydsUrl, configProp.getSignUp().get("ydsUrl"), signUp.getId()+".png");
                    signUp.setYdsUrl(configProp.getSignUp().get("ydsUrl") + signUp.getId() + ".png");
                }
            }

            signUp.setCenterId(signUp2.getCenterId());
            signUp.setCerator(signUp2.getCerator());
            signUp.setCreateTime(signUp2.getCreateTime());
            signUp.setReason(signUp2.getReason());

            signUpDao.save(signUp);
        }else{
            String changeContent = "费用类型 ";
            String changeTableField = "";
            feeType2 = feeTypeDao.findOne(feeType.getId());
            School school = schoolDao.findOne(feeType2.getSchoolId());
            RecruitType recruitType = recruitTypeDao.findOne(feeType2.getTypeId());
            Level level = levelDao.findOne(feeType2.getLevelId());
            changeContent += school.getName()+recruitType.getName()+level.getName()+feeType2.getBatch()+"  ";

            if(!feeType.getName().equals(feeType2.getName())){
                changeContent += "费用类型<span style='color:red'>"+feeType2.getName()+"</span>变更为<span style='color:red'>"+feeType.getName()+"</span>  ";
                changeTableField += "name='"+feeType.getName()+"',";
            }
            if(feeType.getFee() != feeType2.getFee()){
                changeContent += "应交金额<span style='color:red'>"+feeType2.getFeeStr()+"</span>变更为<span style='color:red'>"+feeType.getFeeStr()+"</span>  ";
                changeTableField += "fee="+feeType.getFee()+",";
            }
            if(feeType.getSchoolId() != feeType2.getSchoolId()){
                School school2 = schoolDao.findOne(feeType.getSchoolId());
                changeContent += "高校<span style='color:red'>"+school.getName()+"</span>变更为<span style='color:red'>"+school2.getName()+"</span>  ";
                changeTableField += "school_id="+feeType.getSchoolId() +",";
            }
            if(feeType.getTypeId() != feeType2.getTypeId()){
                RecruitType recruitType2 = recruitTypeDao.findOne(feeType.getTypeId());
                changeContent += "招生类型<span style='color:red'>"+recruitType.getName()+"</span>变更为<span style='color:red'>"+recruitType2.getName()+"</span>  ";
                changeTableField += "type_id="+feeType.getTypeId() +",";
            }
            if(feeType.getLevelId() != feeType2.getLevelId()){
                Level level2 = levelDao.findOne(feeType.getLevelId());
                changeContent += "层次<span style='color:red'>"+level.getName()+"</span>变更为<span style='color:red'>"+level2.getName()+"</span>  ";
                changeTableField += "level_id="+feeType.getLevelId() +",";
            }
            if(feeType.getYear() != feeType2.getYear() || feeType.getTerm() != feeType2.getTerm()){
                changeContent += "批次<span style='color:red'>"+feeType2.getBatch()+"</span>变更为<span style='color:red'>"+feeType.getBatch()+"</span>  ";
                changeTableField += "year="+feeType.getYear() +",term="+feeType.getTerm()+",";
            }
            if(changeTableField.length() > 0){
                changeTableField = changeTableField.substring(0, changeTableField.length()-1);
            }

            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent(changeContent);
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_EDIT);
            dataChange.setChangeTable("sign_up");
            dataChange.setChangeTableId(signUp.getId());
            dataChange.setChangeTableField(changeTableField);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
