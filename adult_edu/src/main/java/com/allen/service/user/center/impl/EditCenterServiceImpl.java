package com.allen.service.user.center.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.user.center.CenterDao;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.Center;
import com.allen.entity.user.User;
import com.allen.service.user.center.EditCenterService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditCenterServiceImpl implements EditCenterService {

    @Autowired
    private CenterDao centerDao;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void edit(Center center, String operator) throws Exception {
        Center center2 = centerDao.findByCode(center.getCode());
        if(null != center2 && center2.getId() != center.getId()){
            throw new BusinessException("编号已存在！");
        }
        if(null == center2){
            center2 = centerDao.findOne(center.getId());
        }
        center2.setCode(center.getCode());
        center2.setName(center.getName());
        center2.setLinkman(center.getLinkman());
        center2.setPhone(center.getPhone());
        center2.setAuthorizeDate(center.getAuthorizeDate());
        center2.setFeeState(center.getFeeState());
        center2.setState(center.getState());
        center2.setRemark(center.getRemark());
        center2.setOperator(operator);
        center2.setOperateTime(DateUtil.getLongNowTime());
        centerDao.save(center2);

        //查询用户信息
        User user = userDao.findByLoginName(center2.getPhone());
        user.setLoginName(center2.getPhone());
        user.setName(center2.getLinkman());
        user.setPhone(center2.getPhone());
        userDao.save(user);
    }
}
