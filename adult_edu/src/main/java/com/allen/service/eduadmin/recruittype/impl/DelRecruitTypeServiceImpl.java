package com.allen.service.eduadmin.recruittype.impl;

import com.allen.dao.datachange.DataChangeDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.user.User;
import com.allen.service.eduadmin.recruittype.DelRecruitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/6/29.
 */
@Service
public class DelRecruitTypeServiceImpl implements DelRecruitTypeService {

    @Autowired
    private RecruitTypeDao recruitTypeDao;
    @Autowired
    private DataChangeDao dataChangeDao;

    @Override
    public void del(long id, long centerId, int isAudit, long operateId, String editReson) throws Exception {
        //查询操作是否需要审核
        if(isAudit == User.ISOPERATEAUDIT_NOT) {
            recruitTypeDao.delete(id);
        }else{
            RecruitType recruitType = recruitTypeDao.findOne(id);
            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent("删除了名称为"+recruitType.getName()+"的招生类型");
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_DEL);
            dataChange.setChangeTable("recruit_type");
            dataChange.setChangeTableId(id);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
