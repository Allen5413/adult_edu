package com.allen.dao.eduadmin.recruittype;

import com.allen.entity.eduadmin.RecruitType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/6/29.
 */
public interface RecruitTypeDao extends CrudRepository<RecruitType, Long> {
    public List<RecruitType> findByCenterId(long centerId)throws Exception;
    public RecruitType findByCenterIdAndName(long centerId, String name)throws Exception;

    /**
     * 查询一个学校下的招生类型
     * @param schoolId
     * @return
     * @throws Exception
     */
    @Query("select DISTINCT rt from RecruitType rt, SchoolTypeLevelSpec stls where rt.id = stls.recruitTypeId and stls.schoolId = ?1 order by rt.id")
    public List<RecruitType> findBySchoolId(long schoolId)throws Exception;

    /**
     * 查询一个学校下设置了教学计划的招生类型
     * @param schoolId
     * @return
     * @throws Exception
     */
    @Query("select DISTINCT rt from RecruitType rt, TeachPlan tp where rt.id = tp.typeId and NOW() BETWEEN tp.beginDate and tp.endDate and tp.schoolId = ?1 order by rt.id")
    public List<RecruitType> findBySchoolIdForTeachPlan(long schoolId)throws Exception;
}
