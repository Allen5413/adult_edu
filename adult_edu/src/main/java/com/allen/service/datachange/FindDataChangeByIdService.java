package com.allen.service.datachange;

import com.allen.entity.datachange.DataChange;

/**
 * Created by Allen on 2017/7/3.
 */
public interface FindDataChangeByIdService {
    public DataChange find(long id)throws Exception;
}
