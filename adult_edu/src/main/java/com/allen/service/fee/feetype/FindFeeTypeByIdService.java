package com.allen.service.fee.feetype;

import com.allen.entity.fee.FeeType;

/**
 * Created by Allen on 2017/7/17.
 */
public interface FindFeeTypeByIdService {
    public FeeType find(long id)throws Exception;
}
