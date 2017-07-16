package com.allen.dao.basic.level;

import com.allen.entity.basic.Level;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/7/6.
 */
public interface LevelDao extends CrudRepository<Level, Long> {
    public List<Level> findByCenterId(long centerId)throws Exception;
    public Level findByCenterIdAndName(long centerId, String name)throws Exception;

    /**
     * 查询一个学校下关联的专业的层次
     * @param schoolId
     * @return
     * @throws Exception
     */
    @Query("select DISTINCT l from  Level l, SchoolTypeLevelSpec stls where l.id = stls.levelId and stls.schoolId = ?1 order by l.id")
    public List<Level> findBySchoolFromStls(long schoolId)throws Exception;

    /**
     * 查询一个学校下关联的招生类型下的层次
     * @param schoolId
     * @return
     * @throws Exception
     */
    @Query("select DISTINCT l from  Level l, SchoolTypeLevelSpec stls where l.id = stls.levelId and stls.schoolId = ?1 and stls.recruitTypeId = ?2 order by l.id")
    public List<Level> findBySchoolIdAndTypeId(long schoolId, long typeId)throws Exception;
}
