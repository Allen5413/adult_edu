package com.allen.service.basic.spec.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.entity.basic.School;
import com.allen.entity.basic.Spec;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.user.User;
import com.allen.service.basic.spec.EditSpecService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class EditSpecServiceImpl implements EditSpecService {

    @Autowired
    private SpecDao specDao;
    @Autowired
    private DataChangeDao dataChangeDao;
    @Autowired
    private SchoolDao schoolDao;

    @Override
    public void edit(Spec spec, long centerId, int isOperateAudit, long operateId, String editReson) throws Exception {
        Spec spec2 = specDao.findByCodeAndSchoolId(spec.getCode(), spec.getSchoolId());
        if(spec2 != null && spec2.getId() != spec.getId() && spec.getCode().equals(spec2.getCode())){
            throw new BusinessException("专业编号已存在");
        }
        if(null == spec2){
            spec2 = specDao.findOne(spec.getId());
        }
        //查询操作是否需要审核
        if(isOperateAudit == User.ISOPERATEAUDIT_NOT) {
            spec2.setCode(spec.getCode());
            spec2.setName(spec.getName());
            specDao.save(spec2);
        }else {
            String changeContent = "";
            String changeField = "";
            if(!spec2.getCode().equals(spec.getCode())){
                changeContent += "专业编号为<span style='color:red'>"+spec2.getCode()+"</span>变更为<span style='color:red'>"+spec.getCode()+"</span>；";
                changeField += "code='"+spec.getCode()+"',";
            }
            if(!spec2.getName().equals(spec.getName())){
                changeContent += "专业名称为<span style='color:red'>"+spec2.getName()+"</span>变更为<span style='color:red'>"+spec.getName()+"</span>；";
                changeField += "name='"+spec.getName()+"',";
            }
            if(!StringUtil.isEmpty(changeField)){
                changeField = changeField.substring(0, changeField.length()-1);
                School school = schoolDao.findOne(spec.getSchoolId());
                changeContent = school.getName()+"的"+changeContent;
            }
            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent(changeContent);
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_EDIT);
            dataChange.setChangeTable("spec");
            dataChange.setChangeTableId(spec.getId());
            dataChange.setChangeTableField(changeField);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
