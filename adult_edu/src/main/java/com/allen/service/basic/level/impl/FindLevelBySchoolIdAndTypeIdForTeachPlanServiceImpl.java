package com.allen.service.basic.level.impl;

import com.allen.dao.basic.level.LevelDao;
import com.allen.entity.basic.Level;
import com.allen.service.basic.level.FindLevelBySchoolIdAndTypeIdForTeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
@Service
public class FindLevelBySchoolIdAndTypeIdForTeachPlanServiceImpl implements FindLevelBySchoolIdAndTypeIdForTeachPlanService {

    @Autowired
    private LevelDao levelDao;

    @Override
    public List<Level> find(long schoolId, long typeId)throws Exception{
        return levelDao.findBySchoolIdAndTypeIdForTeachPlan(schoolId, typeId);
    }
}
