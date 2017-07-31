package com.allen.dao.recruit.signup;

import com.allen.entity.recruit.SignUp;
import org.springframework.data.repository.CrudRepository;

public interface SignUpDao extends CrudRepository<SignUp, Long> {
    public SignUp findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndIdCard(long centerId, long schoolId, long recruitTypeId, long levelId, long specId, long tpId, String idCard)throws Exception;
    public SignUp findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndPhone(long centerId, long schoolId, long recruitTypeId, long levelId, long specId, long tpId, String phone)throws Exception;
}
