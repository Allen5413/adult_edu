package com.allen.service.eduadmin.recruittype.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.service.eduadmin.recruittype.AddRecruitTypeService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddRecruitTypeServiceImpl implements AddRecruitTypeService {

    @Autowired
    private RecruitTypeDao recruitTypeDao;

    @Override
    public void add(RecruitType recruitType) throws Exception {
        RecruitType recruitType2 = recruitTypeDao.findByCenterIdAndName(recruitType.getCenterId(), recruitType.getName());
        if(null != recruitType2 && !StringUtil.isEmpty(recruitType2.getName())){
            throw new BusinessException("招生类型已存在！");
        }
        recruitTypeDao.save(recruitType);
    }
}
