package com.allen.dao.eduadmin.teachplancourse;

import com.allen.entity.eduadmin.TeachPlanCourse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/7/15 0015.
 */
public interface TeachPlanCourseDao extends CrudRepository<TeachPlanCourse, Long> {
    public TeachPlanCourse findByTeachPlanIdAndSemesterAndCourseId(long tpId, String semester, long courseId)throws Exception;

    @Modifying
    @Query("delete from TeachPlanCourse where teachPlanId = ?1")
    public void delByTeachPlanId(long teachPlanId)throws Exception;
}
