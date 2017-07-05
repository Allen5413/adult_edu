package com.allen.service.basic.school.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.EditSchoolService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditSchoolServiceImpl implements EditSchoolService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public void edit(School school, String operator) throws Exception {
        School school2 = schoolDao.findByCenterIdAndCode(school.getCenterId(), school.getCode());
        if(null != school2 && school2.getId() != school.getId()){
            throw new BusinessException("编号已存在！");
        }
        school2 = schoolDao.findByCenterIdAndName(school.getCenterId(), school.getName());
        if(null != school2 && school2.getId() != school.getId()){
            throw new BusinessException("名称已存在！");
        }
        if(null == school2){
            school2 = schoolDao.findOne(school.getId());
        }
        school2.setAddress(school.getAddress());
        school2.setLinkMan(school.getLinkMan());
        school2.setUserId(school.getUserId());
        school2.setPhone(school.getPhone());
        school2.setRemark(school.getRemark());
        school2.setOperator(operator);
        school2.setOperateTime(DateUtil.getLongNowTime());
        schoolDao.save(school2);
    }
}
