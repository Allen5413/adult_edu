package com.allen.service.user.center.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.user.center.FindCenterDao;
import com.allen.service.user.center.PageCenterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageCenterServiceImpl implements PageCenterService {

    @Resource
    private FindCenterDao findCenterDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findCenterDao.findPage(pageInfo, params, sortMap);
    }
}
