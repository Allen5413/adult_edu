package com.allen.dao.fee.feetype;

import com.allen.entity.fee.FeeType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/7/17.
 */
public interface FeeTypeDao extends CrudRepository<FeeType, Long> {
    public FeeType findByNameAndSchoolIdAndTypeIdAndLevelIdAndYearAndTerm(String name, long schoolId, long typeId, long levelId, int year, int term)throws Exception;
}
