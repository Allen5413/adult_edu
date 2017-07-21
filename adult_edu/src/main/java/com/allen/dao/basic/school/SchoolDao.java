package com.allen.dao.basic.school;

import com.allen.entity.basic.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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
}
