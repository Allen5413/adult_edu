package com.allen.service.basic.level.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.entity.basic.Level;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.user.User;
import com.allen.service.basic.level.EditLevelService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditLevelServiceImpl implements EditLevelService {

    @Autowired
    private LevelDao levelDao;
    @Autowired
    private DataChangeDao dataChangeDao;

    @Override
    public void edit(Level level, long centerId, int isAudit, long operateId, String editReson) throws Exception {
        //查询操作是否需要审核
        if(isAudit == User.ISOPERATEAUDIT_NOT) {
            Level level2 = levelDao.findByCenterIdAndName(level.getCenterId(), level.getName());
            if (null != level2 && level2.getId() != level.getId()) {
                throw new BusinessException("名称已存在！");
            }
            if (null == level2) {
                level2 = levelDao.findOne(level.getId());
            }
            level2.setName(level.getName());
            level2.setOperateTime(DateUtil.getLongNowTime());
            levelDao.save(level2);
        }else{
            Level level2 = levelDao.findOne(level.getId());
            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent("层次 "+level2.getName()+" 修改为 "+level.getName());
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_EDIT);
            dataChange.setChangeTable("level");
            dataChange.setChangeTableId(level.getId());
            dataChange.setChangeTableField("name='"+level.getName()+"'");
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
