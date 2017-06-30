package com.allen.service.user.user.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.Center;
import com.allen.entity.user.User;
import com.allen.service.user.center.FindCenterByIdService;
import com.allen.service.user.user.LoginService;
import com.allen.util.DateUtil;
import com.allen.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FindCenterByIdService findCenterByIdService;

    @Override
    public User login(String loginName, String pwd) throws Exception {
        pwd = MD5Util.MD5(pwd);
        User user = userDao.findByLoginNameAndPwd(loginName, pwd);
        if(null != user){
            if(user.getState() == User.STATE_DELETE){
                throw new BusinessException("您的账号已被删除！");
            }
            if(user.getState() == User.STATE_DISABLE){
                throw new BusinessException("您的账号已被停用！");
            }
            if(user.getType() != User.TYPE_ZS_ADMIN){
                //查询用户的中心
                Center center = findCenterByIdService.find(user.getCenterId());
                if(center.getState() == Center.STATE_NOT){
                    throw new BusinessException("您的中心已经被停用了，请联系管理员！");
                }
                //判断授权日期是否过期
                if(System.currentTimeMillis() > center.getAuthorizeDate().getTime()){
                    throw new BusinessException("您的中心授权时间已到期，请联系管理员！");
                }
            }
        }
        return user;
    }
}
