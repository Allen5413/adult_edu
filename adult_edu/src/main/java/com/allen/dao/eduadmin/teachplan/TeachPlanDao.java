package com.allen.dao.eduadmin.teachplan;

import com.allen.entity.eduadmin.TeachPlan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/6/29.
 */
public interface TeachPlanDao extends CrudRepository<TeachPlan, Long> {
    public List<TeachPlan> findByCenterId(long centerId)throws Exception;
    public TeachPlan findBySchoolIdAndTypeIdAndLevelIdAndSpecIdAndYearAndTerm(long schoolId, long typeId, long levelId, long specId, int year, int term)throws Exception;

    @Query("select tp from TeachPlan tp where now() BETWEEN tp.beginDate and tp.endDate and tp.schoolId = ?1 and tp.typeId = ?2 and tp.levelId = ?3 AND tp.specId = ?4 order by tp.year, tp.term")
    public List<TeachPlan> findBySchoolIdAndTypeIdAndLevelIdAndSpecId(long schoolId, long typeId, long levelId, long specId)throws Exception;

    /**
     * 统计一个学习中心下的相同年季的批次数量
     * @param centerId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from (select DISTINCT tp.year, tp.term from teach_plan tp where tp.center_id = ?1) t")
    public BigInteger countNumForYearANdTermByCenterId(long centerId)throws Exception;
}
