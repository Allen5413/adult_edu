package com.allen.service.fee.feetype;

import com.allen.entity.fee.FeeType;

/**
 * Created by Allen on 2017/6/28.
 */
public interface EditFeeTypeService {
    public void edit(FeeType feeType, long centerId, int isAudit, long operateId, String editReson)throws Exception;
}
