package com.allen.service.fee.studentfee.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.fee.feetype.FindFeeTypeDao;
import com.allen.dao.fee.studentfee.FindStudentFeeDao;
import com.allen.service.fee.feetype.PageFeeTypeService;
import com.allen.service.fee.studentfee.PageStudentFeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2016/12/20.
 */
@Service
public class PageStudentFeeServiceImpl implements PageStudentFeeService {

    @Resource
    private FindStudentFeeDao findStudentFeeDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap) throws Exception {
        return findStudentFeeDao.find(pageInfo, params, sortMap);
    }
}
