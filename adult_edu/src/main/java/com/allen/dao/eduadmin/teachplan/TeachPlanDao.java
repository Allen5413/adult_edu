package com.allen.dao.eduadmin.teachplan;

import com.allen.entity.eduadmin.TeachPlan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/6/29.
 */
public interface TeachPlanDao extends CrudRepository<TeachPlan, Long> {
    public List<TeachPlan> findByCenterId(long centerId)throws Exception;
    public TeachPlan findBySchoolIdAndTypeIdAndLevelIdAndSpecIdAndYearAndTerm(long schoolId, long typeId, long levelId, long specId, int year, int term)throws Exception;
}
