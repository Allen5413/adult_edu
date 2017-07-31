package com.allen.service.eduadmin.student.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.entity.eduadmin.Student;
import com.allen.service.eduadmin.student.AddStudentService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/31.
 */
@Service
public class AddStudentServiceImpl implements AddStudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void add(Student student) throws Exception {
        if(StringUtil.isEmpty(student.getCode())){
            throw new BusinessException("学生的学号不能为空");
        }
        Student student2 = studentDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndIdCard(student.getCenterId(), student.getSchoolId(), student.getRecruitTypeId(), student.getLevelId(), student.getSpecId(), student.getTeachPlanId(), student.getIdCard());
        if(null != student2){
            throw new BusinessException("学生的身份证号再同一个教学计划里面已经存在");
        }
        student2 = studentDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndPhone(student.getCenterId(), student.getSchoolId(), student.getRecruitTypeId(), student.getLevelId(), student.getSpecId(), student.getTeachPlanId(), student.getPhone());
        if(null != student2){
            throw new BusinessException("学生的手机号码再同一个教学计划里面已经存在");
        }
        student2 = studentDao.findBySchoolIdAndCode(student.getSchoolId(), student.getCode());
        if(null != student2){
            throw new BusinessException("学生的学号已经存在");
        }
        studentDao.save(student);
    }
}
