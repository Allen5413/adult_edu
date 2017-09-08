package com.allen.service.fee.studentfee.impl;

import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.fee.FeeType;
import com.allen.entity.fee.StudentFee;
import com.allen.service.fee.studentfee.SetCleanFeeStudentService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Allen on 2017/9/8.
 */
@Service
public class SetCleanFeeStudentServiceImpl implements SetCleanFeeStudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeachPlanDao teachPlanDao;
    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private StudentFeeDao studentFeeDao;

    @Override
    @Transactional
    public void set(String studentIds) throws Exception {
        if(!StringUtil.isEmpty(studentIds)){
            String[] studentIdArray = studentIds.split(",");
            for(String studentId : studentIdArray){
                long sId = Long.parseLong(studentId);
                Student student = studentDao.findOne(sId);
                if(null != student) {
                    TeachPlan teachPlan = teachPlanDao.findOne(student.getTeachPlanId());
                    if(null != teachPlan) {
                        List<FeeType> feeTypeList = feeTypeDao.findBySchoolIdAndTypeIdAndLevelIdAndYearAndTerm(student.getSchoolId(), student.getRecruitTypeId(),
                                student.getLevelId(), teachPlan.getYear(), teachPlan.getTerm());
                        List<StudentFee> studentFeeList = studentFeeDao.findByStudentId(student.getId());
                        boolean isFeeOver = true;
                        for(FeeType feeType : feeTypeList){
                            long fee = feeType.getFee();
                            long fee2 = 0;
                            if(null != studentFeeList && 0 < studentFeeList.size()) {
                                for (StudentFee studentFee : studentFeeList){
                                    if(feeType.getId() == studentFee.getFeeTypeId()){
                                        fee2 += studentFee.getFee();
                                    }
                                }
                            }
                            if(fee2 < fee){
                                isFeeOver = false;
                                break;
                            }
                        }
                        if(isFeeOver) {
                            student.setFeeState(Student.FEE_STATE_OVER);
                            studentDao.save(student);
                        }
                    }
                }
            }
        }
    }
}
