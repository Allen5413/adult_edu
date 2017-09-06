package com.allen.dao.eduadmin.studentcourse;

import com.allen.entity.eduadmin.StudentCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/8/17.
 */
public interface StudentCourseDao extends CrudRepository<StudentCourse, Long> {
    public StudentCourse findByStudentIdAndCourseId(long studentId, long courseId)throws Exception;

    /**
     * 查询一个批次下的学生的成绩录入情况
     * @param tpId
     * @return
     * @throws Exception
     */
    @Query("select sc from Student s, StudentCourse sc where s.id = sc.studentId and s.teachPlanId = ?1")
    public List<StudentCourse> findByTeachPlanId(long tpId)throws Exception;
}
