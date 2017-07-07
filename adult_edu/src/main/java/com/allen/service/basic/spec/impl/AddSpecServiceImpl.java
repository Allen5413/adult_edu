package com.allen.service.basic.spec.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.entity.basic.Spec;
import com.allen.service.basic.spec.AddSpecService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class AddSpecServiceImpl implements AddSpecService {

    @Autowired
    private SpecDao specDao;

    @Override
    public Spec add(Spec spec) throws Exception {
        Spec spec2 = specDao.findByCodeAndSchoolId(spec.getCode(), spec.getSchoolId());
        if(spec2 != null && !StringUtil.isEmpty(spec2.getName())){
            throw new BusinessException("专业编号已存在");
        }
        return specDao.save(spec);
    }
}
