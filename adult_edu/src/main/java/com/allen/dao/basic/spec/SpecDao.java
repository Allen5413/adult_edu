package com.allen.dao.basic.spec;

import com.allen.entity.basic.Spec;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/7/7.
 */
public interface SpecDao extends CrudRepository<Spec, Long> {
    public Spec findByCodeAndSchoolId(String code, long schoolId)throws Exception;
}
