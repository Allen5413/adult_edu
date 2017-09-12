package com.allen.service.notify.impl;

import com.allen.dao.notify.NotifyDao;
import com.allen.entity.notify.Notify;
import com.allen.service.notify.AuditNotifyService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/9/11.
 */
@Service
public class AuditNotifyServiceImpl implements AuditNotifyService {

    @Autowired
    private NotifyDao notifyDao;

    public void auditPass(long id, String operator)throws Exception{
        Notify notify = notifyDao.findOne(id);
        notify.setState(Notify.STATE_PASS);
        notify.setOperator(operator);
        notify.setOperateTime(DateUtil.getLongNowTime());
        notifyDao.save(notify);
    }

    public void auditNot(long id, String refuseRemark, String operator)throws Exception{
        Notify notify = notifyDao.findOne(id);
        notify.setState(Notify.STATE_NOT);
        notify.setRefuseRemark(refuseRemark);
        //不通过暂时不记录，记录后会影响查询的编辑时间
//        notify.setOperator(operator);
//        notify.setOperateTime(DateUtil.getLongNowTime());
        notifyDao.save(notify);
    }
}
