package com.allen.service.notify.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.notify.FindNotifyDao;
import com.allen.service.notify.PageNotifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageNotifyServiceImpl implements PageNotifyService {

    @Resource
    private FindNotifyDao findNotifyDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, String> params) throws Exception {
        return findNotifyDao.findPage(pageInfo, params);
    }
}
