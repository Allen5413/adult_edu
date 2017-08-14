package com.allen.service.user.user;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
public interface PageUserService {
    public PageInfo find(PageInfo pageInfo, Map<String, String> params, String orderBy)throws Exception;
}
