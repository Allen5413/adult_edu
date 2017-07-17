package com.allen.service.fee.feetype.impl;

import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.entity.fee.FeeType;
import com.allen.service.fee.feetype.FindFeeTypeByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/17.
 */
@Service
public class FindFeeTypeByIdServiceImpl implements FindFeeTypeByIdService {

    @Autowired
    private FeeTypeDao feeTypeDao;

    @Override
    public FeeType find(long id) throws Exception {
        return feeTypeDao.findOne(id);
    }
}
