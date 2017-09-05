package com.allen.dao.basic.course;

import com.allen.entity.basic.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/7/10.
 */
public interface CourseDao extends CrudRepository<Course, Long> {
    public Course findByCodeAndSchoolId(String code, long schoolId)throws Exception;

    @Query("select DISTINCT c from TeachPlanCourse tpc, TeachPlan tp, Course c where tpc.teachPlanId = tp.id and tpc.courseId = c.id and tp.specId = ?1 order by c.code")
    public List<Course> findBySpecId(long specId)throws Exception;
}
