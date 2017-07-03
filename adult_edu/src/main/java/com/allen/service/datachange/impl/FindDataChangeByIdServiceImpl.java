package com.allen.service.datachange.impl;

import com.allen.dao.datachange.DataChangeDao;
import com.allen.entity.datachange.DataChange;
import com.allen.service.datachange.FindDataChangeByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/3.
 */
@Service
public class FindDataChangeByIdServiceImpl implements FindDataChangeByIdService {

    @Autowired
    private DataChangeDao dataChangeDao;

    @Override
    public DataChange find(long id) throws Exception {
        return dataChangeDao.findOne(id);
    }
}
