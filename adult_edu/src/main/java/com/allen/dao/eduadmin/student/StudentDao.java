package com.allen.dao.eduadmin.student;

import com.allen.entity.eduadmin.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/7/27.
 */
public interface StudentDao extends CrudRepository<Student, Long> {
    public Student findBySchoolIdAndCode(long schoolId, String code)throws Exception;
    public Student findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndIdCard(long centerId, long schoolId, long typeId, long levelId, long specId, long tpId, String idCard)throws Exception;
    public Student findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndPhone(long centerId, long schoolId, long typeId, long levelId, long specId, long tpId, String phone)throws Exception;
}
