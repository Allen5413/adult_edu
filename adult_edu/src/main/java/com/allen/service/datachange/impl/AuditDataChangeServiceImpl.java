package com.allen.service.datachange.impl;

import com.allen.dao.datachange.AuditDataChangeDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.entity.datachange.DataChange;
import com.allen.service.datachange.AuditDataChangeService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/7/3.
 */
@Service
public class AuditDataChangeServiceImpl implements AuditDataChangeService {

    @Autowired
    private DataChangeDao dataChangeDao;
    @Autowired
    private AuditDataChangeDao auditDataChangeDao;

    @Override
    @Transactional
    public void audit(long id, int state, String refuseContent)throws Exception{
        DataChange dataChange = dataChangeDao.findOne(id);
        dataChange.setState(state);
        dataChange.setRefuseContent(refuseContent);
        dataChangeDao.save(dataChange);
        if(state == DataChange.STATE_AUDIT_PASS){
            if(dataChange.getType() == DataChange.TYPE_DEL){
                String sql = "delete from "+dataChange.getChangeTable()+ " where id = "+dataChange.getChangeTableId();
                auditDataChangeDao.auditPassOperate(sql);
                if("teach_plan".equals(dataChange.getChangeTable())){
                    sql = "delete from teach_plan_course where teach_plan_id = "+dataChange.getChangeTableId();
                    auditDataChangeDao.auditPassOperate(sql);
                }

            }
            if(dataChange.getType() == DataChange.TYPE_EDIT && !StringUtil.isEmpty(dataChange.getChangeTableField())){
                String sql = "update "+dataChange.getChangeTable()+ " set " + dataChange.getChangeTableField()+" where id = "+dataChange.getChangeTableId();
                auditDataChangeDao.auditPassOperate(sql);
            }
        }
    }
}
