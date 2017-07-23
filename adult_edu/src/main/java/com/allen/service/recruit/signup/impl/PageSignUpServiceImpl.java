package com.allen.service.recruit.signup.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.recruit.signup.FindSignUpDao;
import com.allen.service.recruit.signup.PageSignUpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageSignUpServiceImpl implements PageSignUpService {

    @Resource
    private FindSignUpDao findSignUpDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findSignUpDao.findPage(pageInfo, params, sortMap);
    }
}
