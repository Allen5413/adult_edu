package com.allen.service.basic.level.impl;

import com.allen.dao.basic.level.LevelDao;
import com.allen.entity.basic.Level;
import com.allen.service.basic.level.FindLevelBySchoolIdFromStlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/15 0015.
 */
@Service
public class FindLevelBySchoolIdFromStlsServiceImpl implements FindLevelBySchoolIdFromStlsService {

    @Autowired
    public LevelDao levelDao;

    @Override
    public List<Level> find(long schoolId) throws Exception {
        return levelDao.findBySchoolFromStls(schoolId);
    }
}
