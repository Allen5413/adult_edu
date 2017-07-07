package com.allen.service.basic.level.impl;

import com.allen.dao.basic.level.LevelDao;
import com.allen.entity.basic.Level;
import com.allen.service.basic.level.FindLevelByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class FindLevelByCenterIdServiceImpl implements FindLevelByCenterIdService {
    @Autowired
    private LevelDao levelDao;

    @Override
    public List<Level> find(long centerId) throws Exception {
        return levelDao.findByCenterId(centerId);
    }
}
