package com.allen.service.fee.feetype;

import com.allen.entity.fee.FeeType;

import java.util.List;

/**
 * Created by Allen on 2017/8/3.
 */
public interface FindFeeTypeBySchoolIdAndTypeIdAndLevelIdAndYearAndTermService {
    public List<FeeType> find(long schoolId, long typeId, long levelId, int year, int term)throws Exception;
}
