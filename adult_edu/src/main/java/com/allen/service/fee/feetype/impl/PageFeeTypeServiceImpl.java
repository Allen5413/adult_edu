package com.allen.service.fee.feetype.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.fee.feetype.FindFeeTypeDao;
import com.allen.service.fee.feetype.PageFeeTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageFeeTypeServiceImpl implements PageFeeTypeService {

    @Resource
    private FindFeeTypeDao findFeeTypeDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findFeeTypeDao.find(pageInfo, params, sortMap);
    }
}
