package com.allen.service.basic.school.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.AddSchoolService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddSchoolServiceImpl implements AddSchoolService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public void add(School school) throws Exception {
        School school2 = schoolDao.findByCenterIdAndCode(school.getCenterId(), school.getCode());
        if(null != school2 && !StringUtil.isEmpty(school2.getCode())){
            throw new BusinessException("编号已存在！");
        }
        school2 = schoolDao.findByCenterIdAndName(school.getCenterId(), school.getName());
        if(null != school2 && !StringUtil.isEmpty(school2.getCode())){
            throw new BusinessException("名称已存在！");
        }
        schoolDao.save(school);
    }
}
