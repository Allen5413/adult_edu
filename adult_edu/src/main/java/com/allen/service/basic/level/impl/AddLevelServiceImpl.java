package com.allen.service.basic.level.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.entity.basic.Level;
import com.allen.service.basic.level.AddLevelService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddLevelServiceImpl implements AddLevelService {

    @Autowired
    private LevelDao levelDao;

    @Override
    public void add(Level level) throws Exception {
        Level level2 = levelDao.findByCenterIdAndName(level.getCenterId(), level.getName());
        if(null != level2 && !StringUtil.isEmpty(level2.getName())){
            throw new BusinessException("名称已存在！");
        }
        levelDao.save(level);
    }
}
