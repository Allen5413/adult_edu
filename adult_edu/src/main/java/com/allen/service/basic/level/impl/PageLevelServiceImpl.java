package com.allen.service.basic.level.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.basic.level.FindLevelDao;
import com.allen.service.basic.level.PageLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageLevelServiceImpl implements PageLevelService {

    @Resource
    private FindLevelDao findLevelDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findLevelDao.findPage(pageInfo, params, sortMap);
    }
}
