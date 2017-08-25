package com.allen.service.user.user;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2017/8/25.
 */
public interface PageFxsStudentNumForByCenterIdService {
    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception;
}
