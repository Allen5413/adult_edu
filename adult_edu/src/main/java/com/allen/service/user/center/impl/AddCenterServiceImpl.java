package com.allen.service.user.center.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.user.center.CenterDao;
import com.allen.entity.user.Center;
import com.allen.entity.user.User;
import com.allen.service.user.center.AddCenterService;
import com.allen.service.user.user.AddUserService;
import com.allen.util.MD5Util;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddCenterServiceImpl implements AddCenterService {

    @Autowired
    private CenterDao centerDao;
    @Autowired
    private AddUserService addUserService;

    @Override
    @Transactional
    public void add(Center center) throws Exception {
        Center center2 = centerDao.findByCode(center.getCode());
        if(null != center2 && !StringUtil.isEmpty(center2.getCode())){
            throw new BusinessException("编号已存在！");
        }
        centerDao.save(center);

        //添加用户
        User user = new User();
        user.setLoginName(center.getPhone());
        user.setPwd(MD5Util.MD5(center.getPhone().substring(center.getPhone().length()-6, center.getPhone().length())));
        user.setName(center.getLinkman());
        user.setPhone(center.getPhone());
        user.setType(User.TYPE_CENTER_ADMIN);
        user.setState(User.STATE_ENABLE);
        user.setCenterId(center.getId());
        user.setIsOperateAudit(User.ISOPERATEAUDIT_NOT);
        user.setCreator(center.getCerator());
        user.setOperator(center.getOperator());
        addUserService.add(user, 2);
    }
}
