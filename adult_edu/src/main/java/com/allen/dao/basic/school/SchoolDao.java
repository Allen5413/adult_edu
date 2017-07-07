package com.allen.dao.basic.school;

import com.allen.entity.basic.School;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/7/5.
 */
public interface SchoolDao extends CrudRepository<School, Long> {
    public List<School> findByCenterId(long centerId)throws Exception;
    public School findByCenterIdAndCode(long centerId, String code)throws Exception;
    public School findByCenterIdAndName(long centerId, String name)throws Exception;
}
