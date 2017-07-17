package com.allen.service.fee.feetype.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.entity.fee.FeeType;
import com.allen.service.fee.feetype.AddFeeTypeService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddFeeTypeServiceImpl implements AddFeeTypeService {

    @Autowired
    private FeeTypeDao feeTypeDao;

    @Override
    public void add(FeeType feeType) throws Exception {
        FeeType feeType2 = feeTypeDao.findByNameAndSchoolIdAndTypeIdAndLevelIdAndYearAndTerm(feeType.getName(), feeType.getSchoolId(), feeType.getTypeId(), feeType.getLevelId(), feeType.getYear(), feeType.getTerm());
        if(null != feeType2 && !StringUtil.isEmpty(feeType2.getName())){
            throw new BusinessException("费用类型已存在！");
        }
        feeTypeDao.save(feeType);
    }
}
