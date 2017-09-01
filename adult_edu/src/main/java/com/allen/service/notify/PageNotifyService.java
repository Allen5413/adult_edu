package com.allen.service.notify;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
public interface PageNotifyService {
    public PageInfo find(PageInfo pageInfo, Map<String, String> params)throws Exception;
}
