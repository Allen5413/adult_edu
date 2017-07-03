package com.allen.service.datachange.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.datachange.FindDataChangeDao;
import com.allen.dao.user.center.FindCenterDao;
import com.allen.service.datachange.PageDataChangeService;
import com.allen.service.user.center.PageCenterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageDataChangeServiceImpl implements PageDataChangeService {

    @Resource
    private FindDataChangeDao findDataChangeDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, String> params) throws Exception {
        return findDataChangeDao.findPage(pageInfo, params);
    }
}
