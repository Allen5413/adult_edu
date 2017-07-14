package com.allen.service.eduadmin.teachplan.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.eduadmin.recruittype.FindRecruitTypeDao;
import com.allen.dao.eduadmin.teachplan.FindTeachPlanDao;
import com.allen.service.eduadmin.recruittype.PageRecruitTypeService;
import com.allen.service.eduadmin.teachplan.PageTeachPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageTeachPlanServiceImpl implements PageTeachPlanService {

    @Resource
    private FindTeachPlanDao findTeachPlanDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findTeachPlanDao.findPage(pageInfo, params, sortMap);
    }
}
