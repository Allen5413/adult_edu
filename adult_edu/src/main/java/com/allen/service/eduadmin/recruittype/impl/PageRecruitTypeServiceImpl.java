package com.allen.service.eduadmin.recruittype.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.eduadmin.recruittype.FindRecruitTypeDao;
import com.allen.service.eduadmin.recruittype.PageRecruitTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageRecruitTypeServiceImpl implements PageRecruitTypeService {

    @Resource
    private FindRecruitTypeDao findRecruitTypeDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findRecruitTypeDao.findPage(pageInfo, params, sortMap);
    }
}
