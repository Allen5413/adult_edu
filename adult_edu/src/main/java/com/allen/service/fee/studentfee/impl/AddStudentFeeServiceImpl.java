package com.allen.service.fee.studentfee.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.fee.FeeType;
import com.allen.entity.fee.StudentFee;
import com.allen.service.fee.studentfee.AddStudentFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddStudentFeeServiceImpl implements AddStudentFeeService {

    @Autowired
    private StudentFeeDao studentFeeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private TeachPlanDao teachPlanDao;

    @Override
    @Transactional
    public void add(StudentFee studentFee) throws Exception {
        Student student = studentDao.findOne(studentFee.getStudentId());
        if(null == student){
            throw new BusinessException("没有找到学生信息");
        }
        TeachPlan teachPlan = teachPlanDao.findOne(student.getTeachPlanId());
        if(null == teachPlan){
            throw new BusinessException("没有找到该学生的教学计划");
        }
        List<FeeType> feeTypeList = feeTypeDao.findBySchoolIdAndTypeIdAndLevelIdAndYearAndTerm(student.getSchoolId(), student.getRecruitTypeId(),
                student.getLevelId(), teachPlan.getYear(), teachPlan.getTerm());
        if(null == feeTypeList || 1 > feeTypeList.size()){
            throw new BusinessException("没有找到该学生的缴费类型");
        }

        List<StudentFee> studentFeeList = studentFeeDao.findByStudentId(student.getId());
        boolean isFeeOver = true;
        for(FeeType feeType : feeTypeList){
            long fee = feeType.getFee();
            long fee2 = 0;
            if(null != studentFeeList && 0 < studentFeeList.size()) {
                for (StudentFee studentFee2 : studentFeeList){
                    if(feeType.getId() == studentFee2.getFeeTypeId()){
                        fee2 += studentFee2.getFee();
                        if(feeType.getId() == studentFee.getFeeTypeId()){
                            fee2 += studentFee.getFee();
                        }
                    }
                }
            }
            if(fee2 < fee){
                isFeeOver = false;
                break;
            }
        }

        studentFeeDao.save(studentFee);
        student.setFeeState(isFeeOver ? Student.FEE_STATE_OVER : Student.FEE_STATE_ING);
        studentDao.save(student);
    }
}
