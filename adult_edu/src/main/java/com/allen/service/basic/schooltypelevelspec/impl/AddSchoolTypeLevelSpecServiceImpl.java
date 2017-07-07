package com.allen.service.basic.schooltypelevelspec.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.schooltypelevelspec.SchoolTypeLevelSpecDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.entity.basic.Spec;
import com.allen.service.basic.schooltypelevelspec.AddSchoolTypeLevelSpecService;
import com.allen.service.basic.spec.AddSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class AddSchoolTypeLevelSpecServiceImpl implements AddSchoolTypeLevelSpecService {

    @Autowired
    private SchoolTypeLevelSpecDao schoolTypeLevelSpecDao;
    @Autowired
    private SpecDao specDao;
    @Autowired
    private AddSpecService addSpecService;

    @Override
    @Transactional
    public void add(SchoolTypeLevelSpec schoolTypeLevelSpec, String specCode, String specName) throws Exception {
        Spec spec = specDao.findByCodeAndSchoolId(specCode, schoolTypeLevelSpec.getSchoolId());
        if(null != spec){
            if(!spec.getName().equals(specName)) {
                throw new BusinessException("该编号的专业已经存在，名为：" + spec.getName());
            }
            SchoolTypeLevelSpec schoolTypeLevelSpec2 = schoolTypeLevelSpecDao.findBySchoolIdAndRecruitTypeIdAndLevelIdAndSpecId(schoolTypeLevelSpec.getSchoolId(), schoolTypeLevelSpec.getRecruitTypeId(), schoolTypeLevelSpec.getLevelId(), spec.getId());
            if(null == schoolTypeLevelSpec2) {
                schoolTypeLevelSpec.setSpecId(spec.getId());
            }
        }else{
            spec = new Spec();
            spec.setSchoolId(schoolTypeLevelSpec.getSchoolId());
            spec.setCode(specCode);
            spec.setName(specName);
            spec.setCreator(schoolTypeLevelSpec.getOperator());
            spec.setOperator(schoolTypeLevelSpec.getOperator());
            addSpecService.add(spec);
            schoolTypeLevelSpec.setSpecId(spec.getId());
        }
        if(schoolTypeLevelSpec.getSpecId() > 0) {
            schoolTypeLevelSpecDao.save(schoolTypeLevelSpec);
        }
    }
}
