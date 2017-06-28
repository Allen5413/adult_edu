package com.allen.service.user.center.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.user.center.CenterDao;
import com.allen.entity.user.Center;
import com.allen.service.user.center.AddCenterService;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddCenterServiceImpl implements AddCenterService {

    @Resource
    private CenterDao centerDao;

    @Override
    public void add(Center center) throws Exception {
        Center center2 = centerDao.findByCode(center.getCode());
        if(null != center2 && !StringUtil.isEmpty(center2.getCode())){
            throw new BusinessException("编号已存在！");
        }
        centerDao.save(center);
    }
}
