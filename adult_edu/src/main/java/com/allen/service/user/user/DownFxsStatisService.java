package com.allen.service.user.user;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2017/8/23.
 */
public interface DownFxsStatisService {
    public String down(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap, String fileName)throws Exception;
}
