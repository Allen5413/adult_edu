package com.allen.dao.eduadmin.teachplan;

import com.allen.entity.eduadmin.TeachPlan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/6/29.
 */
public interface TeachPlanDao extends CrudRepository<TeachPlan, Long> {
    public List<TeachPlan> findByCenterId(long centerId)throws Exception;
    public TeachPlan findBySchoolIdAndTypeIdAndLevelIdAndSpecIdAndYearAndTerm(long schoolId, long typeId, long levelId, long specId, int year, int term)throws Exception;

    @Query("select tp from TeachPlan tp where now() BETWEEN tp.beginDate and tp.endDate and tp.schoolId = ?1 and tp.typeId = ?2 and tp.levelId = ?3 AND tp.specId = ?4 order by tp.year, tp.term")
    public List<TeachPlan> findBySchoolIdAndTypeIdAndLevelIdAndSpecId(long schoolId, long typeId, long levelId, long specId)throws Exception;
}
