package com.allen.dao.recruit.signup;

import com.allen.entity.recruit.SignUp;
import org.springframework.data.repository.CrudRepository;

public interface SignUpDao extends CrudRepository<SignUp, Long> {
    public SignUp findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndIdCard(long centerId, long schoolId, long recruitTypeId, long levelId, long specId, String idCard)throws Exception;
    public SignUp findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndPhone(long centerId, long schoolId, long recruitTypeId, long levelId, long specId, String phone)throws Exception;
}
