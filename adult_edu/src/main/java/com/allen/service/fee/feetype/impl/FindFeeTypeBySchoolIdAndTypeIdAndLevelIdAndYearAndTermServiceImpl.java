package com.allen.service.fee.feetype.impl;

import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.entity.fee.FeeType;
import com.allen.service.fee.feetype.FindFeeTypeBySchoolIdAndTypeIdAndLevelIdAndYearAndTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/8/3.
 */
@Service
public class FindFeeTypeBySchoolIdAndTypeIdAndLevelIdAndYearAndTermServiceImpl implements FindFeeTypeBySchoolIdAndTypeIdAndLevelIdAndYearAndTermService {

    @Autowired
    private FeeTypeDao feeTypeDao;

    @Override
    public List<FeeType> find(long schoolId, long typeId, long levelId, int year, int term) throws Exception {
        return feeTypeDao.findBySchoolIdAndTypeIdAndLevelIdAndYearAndTerm(schoolId,  typeId, levelId, year, term);
    }
}
