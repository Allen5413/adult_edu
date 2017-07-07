package com.allen.service.basic.level.impl;

import com.allen.dao.basic.level.LevelDao;
import com.allen.entity.basic.Level;
import com.allen.service.basic.level.FindLevelByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/6.
 */
@Service
public class FindLevelByIdServiceImpl implements FindLevelByIdService {

    @Autowired
    private LevelDao levelDao;

    @Override
    public Level find(long id) throws Exception {
        return levelDao.findOne(id);
    }
}
