package com.allen.dao.basic.spec;

import com.allen.entity.basic.Spec;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/7/7.
 */
public interface SpecDao extends CrudRepository<Spec, Long> {
    public Spec findByCodeAndSchoolId(String code, long schoolId)throws Exception;
    public List<Spec> findBySchoolId(long schoolId)throws Exception;

    /**
     * 查询一个学校下关联了招生类型，层次下的专业
     * @param schoolId
     * @return
     * @throws Exception
     */
    @Query("select DISTINCT s from Spec s, SchoolTypeLevelSpec stls where s.id = stls.specId and stls.schoolId = ?1 order by s.code")
    public List<Spec> findBySchoolFromStls(long schoolId)throws Exception;

    /**
     * 查询一个学校下关联的招生类型下的层次下的专业,发布了教学计划的
     * @param schoolId
     * @param typeId
     * @param levelId
     * @return
     * @throws Exception
     */
    @Query("select DISTINCT s from Spec s, TeachPlan tp where s.id = tp.specId and NOW() BETWEEN tp.beginDate and tp.endDate and tp.schoolId = ?1 and tp.typeId = ?2 and tp.levelId = ?3 order by s.code")
    public List<Spec> findBySchoolIdAndTypeIdAndLevelIdForTeachPlan(long schoolId, long typeId, long levelId)throws Exception;


    /**
     * 查询一个学习中心下的专业名称集合，不同高校的相同专业算一个
     * @param centerId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select DISTINCT sp.name from school s, spec sp where s.id = sp.school_id and s.center_id = ?1")
    public List<String> findForNameByCenterId(long centerId)throws Exception;
}
