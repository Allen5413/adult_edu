package com.allen.service.eduadmin.student.impl;

import com.allen.dao.eduadmin.student.FindStudentDao;
import com.allen.service.eduadmin.student.CountStudentNumForStateWhereByCenterIdAndUserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/24.
 */
@Service
public class CountStudentNumForStateWhereByCenterIdAndUserIdServiceImpl implements CountStudentNumForStateWhereByCenterIdAndUserIdService {

    @Autowired
    private FindStudentDao findStudentDao;

    @Override
    public Map find(Long centerId, Long userId) throws Exception {
        List<Map> mapList = findStudentDao.countNumForStateWhereByCenterIdAndUserId(centerId, userId);
        if(null != mapList && 0 < mapList.size()) {
            return mapList.get(0);
        }
        return null;
    }
}
