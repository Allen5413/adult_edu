package com.allen.dao.recruit.signup;

import com.allen.entity.recruit.SignUp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface SignUpDao extends CrudRepository<SignUp, Long> {
    public List<SignUp> findByPhone(String phone)throws Exception;
    public SignUp findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndIdCard(long centerId, long schoolId, long recruitTypeId, long levelId, long specId, long tpId, String idCard)throws Exception;
    public SignUp findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndPhone(long centerId, long schoolId, long recruitTypeId, long levelId, long specId, long tpId, String phone)throws Exception;

    /**
     * 统计一个招生类型下的报名人数
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from sign_up su where su.recruit_type_id = ?1")
    public BigInteger countByRtId(long rtId)throws Exception;


    /**
     * 统计一个招生类型下的一个分销商的报名人数
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from sign_up su where su.recruit_type_id = ?1 and su.user_id = ?2")
    public BigInteger countByRtIdAndUserId(long rtId, long userId)throws Exception;
}
