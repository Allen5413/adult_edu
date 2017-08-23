package com.allen.dao.basic.school;

import com.allen.entity.basic.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/7/5.
 */
public interface SchoolDao extends CrudRepository<School, Long> {
    public List<School> findByCenterId(long centerId)throws Exception;
    public School findByCenterIdAndCode(long centerId, String code)throws Exception;
    public School findByCenterIdAndName(long centerId, String name)throws Exception;

    /**
     * 查询一个中心教学计划里面的学校
     * @param centerId
     * @return
     * @throws Exception
     */
    @Query("select s from School s, TeachPlan tp where s.centerId = ?1 and s.centerId = tp.centerId and s.id = tp.schoolId and NOW() BETWEEN tp.beginDate and tp.endDate order by s.code")
    public List<School> findByCenterIdForTeachPlan(long centerId)throws Exception;

    /**
     * 统计一个学习中心下的高校数量
     * @param centerId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from school s where s.center_id = ?1")
    public BigInteger countNumByCenterId(long centerId)throws Exception;

    /**
     * 统计一个学习中心下的有学生的高校数量
     * @param centerId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from (select DISTINCT sc.id from student s, school sc where s.school_id = sc.id and s.center_id = ?1) t")
    public BigInteger countNumForStudentByCenterId(long centerId)throws Exception;

    /**
     * 查询一个招生类型下关联的高校,发布了教学计划的
     * @param typeId
     * @return
     * @throws Exception
     */
    @Query("select DISTINCT sc from School sc, TeachPlan tp where sc.id = tp.schoolId and NOW() BETWEEN tp.beginDate and tp.endDate and tp.typeId = ?1 order by sc.id")
    public List<School> findByTypeIdForTeachPlan(long typeId)throws Exception;
}
