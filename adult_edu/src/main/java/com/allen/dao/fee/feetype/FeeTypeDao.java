package com.allen.dao.fee.feetype;

import com.allen.entity.fee.FeeType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/7/17.
 */
public interface FeeTypeDao extends CrudRepository<FeeType, Long> {
    public FeeType findByNameAndSchoolIdAndTypeIdAndLevelIdAndYearAndTerm(String name, long schoolId, long typeId, long levelId, int year, int term)throws Exception;
    public List<FeeType> findBySchoolIdAndTypeIdAndLevelIdAndYearAndTerm(long schoolId, long typeId, long levelId, int year, int term)throws Exception;
}
