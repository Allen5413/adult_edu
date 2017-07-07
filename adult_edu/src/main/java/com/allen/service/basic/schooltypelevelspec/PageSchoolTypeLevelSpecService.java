package com.allen.service.basic.schooltypelevelspec;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2017/7/7.
 */
public interface PageSchoolTypeLevelSpecService {
    public PageInfo find(PageInfo pageInfo, Map<String, String> paramsMap)throws Exception;
}
