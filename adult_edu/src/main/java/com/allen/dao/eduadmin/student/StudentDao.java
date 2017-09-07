package com.allen.dao.eduadmin.student;

import com.allen.entity.eduadmin.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/7/27.
 */
public interface StudentDao extends CrudRepository<Student, Long> {
    public Student findBySchoolIdAndCode(long schoolId, String code)throws Exception;
    public Student findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndIdCard(long centerId, long schoolId, long typeId, long levelId, long specId, long tpId, String idCard)throws Exception;
    public Student findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndPhone(long centerId, long schoolId, long typeId, long levelId, long specId, long tpId, String phone)throws Exception;
    public List<Student> findBySchoolIdAndRecruitTypeIdAndLevelIdAndSpecId(long schoolId, long typeId, long levelId, long specId)throws Exception;

    /**
     * 统计一个招生类型下的人数
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from student s where s.recruit_type_id = ?1")
    public BigInteger countNumByRtId(long rtId)throws Exception;

    /**
     * 统计一个招生类型下的一个分销商的报名人数
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from student s where s.recruit_type_id = ?1 and s.user_id = ?2")
    public BigInteger countNumByRtIdAndUserId(long rtId, long userId)throws Exception;

    /**
     * 统计一个学习中心下的人数
     * @param centerId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from student s where s.center_id = ?1")
    public BigInteger countNumByCenterId(long centerId)throws Exception;
}
