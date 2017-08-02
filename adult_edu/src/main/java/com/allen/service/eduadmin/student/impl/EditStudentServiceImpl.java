package com.allen.service.eduadmin.student.impl;

import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.user.User;
import com.allen.service.eduadmin.student.EditStudentService;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditStudentServiceImpl implements EditStudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ConfigProp configProp;
    @Autowired
    private DataChangeDao dataChangeDao;

    @Override
    public void edit(HttpServletRequest request, Student student, long centerId, int isAudit, long operateId, String editReson) throws Exception {
        Student student2 = studentDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndPhone(student.getCenterId(), student.getSchoolId(), student.getRecruitTypeId(), student.getLevelId(), student.getSpecId(), student.getTeachPlanId(), student.getPhone());
        if(null != student2 && student2.getId() != student.getId()){
            throw new BusinessException("手机号码在同一个学校、招生类型、层次、专业、批次下已存在！");
        }

        //查询操作是否需要审核
        if(isAudit == User.ISOPERATEAUDIT_NOT) {
            if (null == student2) {
                student2 = studentDao.findOne(student.getId());
            }
            String xxwUrl = student.getXxwUrl();
            String zkzFrontUrl = student.getZkzFrontUrl();
            String zkzBackUrl = student.getZkzBackUrl();
            if(!xxwUrl.equals(student2.getXxwUrl())){
                if (StringUtil.isEmpty(xxwUrl)) {
                    student.setXxwUrl("");
                }else{
                    UpLoadFileUtil.custFile(request, xxwUrl, configProp.getStudent().get("xxwUrl"), student.getId()+".png");
                    student.setXxwUrl(configProp.getStudent().get("xxwUrl") + student.getId() + ".png");
                }
            }
            if(!zkzFrontUrl.equals(student2.getZkzFrontUrl())){
                if (StringUtil.isEmpty(zkzFrontUrl)) {
                    student.setZkzFrontUrl("");
                }else{
                    UpLoadFileUtil.custFile(request, zkzFrontUrl, configProp.getStudent().get("zkzFrontUrl"), student.getId()+".png");
                    student.setZkzFrontUrl(configProp.getSignUp().get("zkzFrontUrl") + student.getId() + ".png");
                }
            }
            if(!zkzBackUrl.equals(student2.getZkzBackUrl())){
                if (StringUtil.isEmpty(zkzBackUrl)) {
                    student.setZkzBackUrl("");
                }else{
                    UpLoadFileUtil.custFile(request, zkzBackUrl, configProp.getStudent().get("zkzBackUrl"), student.getId()+".png");
                    student.setZkzBackUrl(configProp.getStudent().get("zkzBackUrl") + student.getId() + ".png");
                }
            }

            student.setCenterId(student2.getCenterId());
            student.setUserId(student2.getUserId());
            student.setSchoolId(student2.getSchoolId());
            student.setRecruitTypeId(student2.getRecruitTypeId());
            student.setLevelId(student2.getLevelId());
            student.setSpecId(student2.getSpecId());
            student.setTeachPlanId(student2.getTeachPlanId());
            student.setCode(student2.getCode());
            student.setName(student2.getName());
            student.setSex(student2.getSex());
            student.setIdCard(student2.getIdCard());
            student.setBirthday(student2.getBirthday());
            student.setSignUpDate(student2.getSignUpDate());
            student.setStudyType(student2.getStudyType());
            student.setPhotoUrl(student2.getPhotoUrl());
            student.setIdCardFrontUrl(student2.getIdCardFrontUrl());
            student.setIdCardBackUrl(student2.getIdCardBackUrl());
            student.setDiplomaUrl(student2.getDiplomaUrl());
            student.setYdsUrl(student2.getYdsUrl());
            student.setCerator(student2.getCerator());
            student.setCreateTime(student2.getCreateTime());

            studentDao.save(student);
        }else{
            student2 = studentDao.findOne(student.getId());
            String changeContent = "学号："+student2.getCode()+"；姓名："+student2.getName()+"的学生信息  ";
            String changeTableField = "";
            if(student.getState() != student2.getState()) {
                if (student.getState() == Student.STATE_NORMAL){
                    changeContent += "学籍状态变更为<span style='color:red'>在籍</span>  ";
                    changeTableField += "state=" + Student.STATE_NORMAL + ",";
                }
                if (student.getState() == Student.STATE_REST){
                    changeContent += "学籍状态变更为<span style='color:red'>休学</span>  ";
                    changeTableField += "state=" + Student.STATE_REST + ",";
                }
                if (student.getState() == Student.STATE_QUIT){
                    changeContent += "学籍状态变更为<span style='color:red'>退学</span>  ";
                    changeTableField += "state=" + Student.STATE_QUIT + ",";
                }
                if (student.getState() == Student.STATE_BY){
                    changeContent += "学籍状态变更为<span style='color:red'>毕业</span>  ";
                    changeTableField += "state=" + Student.STATE_BY + ",";
                }
            }
            if(!student.getZipCode().equals(student2.getZipCode()) && !StringUtil.isEmpty(student.getZipCode()) && !StringUtil.isEmpty(student2.getZipCode())){
                changeContent += "邮政编码<span style='color:red'>"+student2.getZipCode()+"</span>变更为<span style='color:red'>"+student.getZipCode()+"</span>  ";
                changeTableField += "zip_code='"+student.getZipCode()+"',";
            }
            if(!student.getPhone().equals(student2.getPhone())  && !StringUtil.isEmpty(student.getPhone()) && !StringUtil.isEmpty(student2.getPhone())){
                changeContent += "手机号码<span style='color:red'>"+student2.getPhone()+"</span>变更为<span style='color:red'>"+student.getPhone()+"</span>  ";
                changeTableField += "phone="+student.getPhone() +",";
            }
            if(!student.getTel().equals(student2.getTel()) && !StringUtil.isEmpty(student.getTel()) && !StringUtil.isEmpty(student2.getTel())){
                changeContent += "联系电话<span style='color:red'>"+student2.getTel() + "</span>变更为<span style='color:red'>"+student.getTel() + "</span>  ";
                changeTableField += "tel="+student.getTel() +",";
            }
            if(!student.getEmail().equals(student2.getEmail()) && !StringUtil.isEmpty(student.getEmail()) && !StringUtil.isEmpty(student2.getEmail())){
                changeContent += "电子邮箱<span style='color:red'>"+student2.getEmail() + "</span>变更为<span style='color:red'>"+student.getEmail() + "</span>  ";
                changeTableField += "email="+student.getEmail() +",";
            }
            if(!student.getAddress().equals(student2.getAddress())  && !StringUtil.isEmpty(student.getAddress()) && !StringUtil.isEmpty(student2.getAddress())){
                changeContent += "通讯地址<span style='color:red'>"+student2.getAddress() + "</span>变更为<span style='color:red'>"+student.getAddress() + "</span>  ";
                changeTableField += "address="+student.getAddress() +",";
            }
            if(!student.getXxwUrl().equals(student2.getXxwUrl())){
                changeTableField += "xxw_url='"+student.getXxwUrl() + "@#@xxw_url',";
            }
            if(!student.getZkzFrontUrl().equals(student2.getZkzFrontUrl())){
                changeTableField += "zkz_front_url='"+student.getZkzFrontUrl() + "@#@zkz_front_url',";
            }
            if(!student.getZkzBackUrl().equals(student2.getZkzBackUrl())){
                changeTableField += "zkz_back_url='"+student.getZkzBackUrl() + "@#@zkz_back_url',";
            }

            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent(changeContent);
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_EDIT);
            dataChange.setChangeTable("student");
            dataChange.setStudentName(student2.getName());
            dataChange.setChangeTableId(student.getId());
            dataChange.setChangeTableField(changeTableField);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
