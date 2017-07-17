package com.allen.service.fee.feetype.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.School;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.fee.FeeType;
import com.allen.entity.user.User;
import com.allen.service.basic.level.EditLevelService;
import com.allen.service.fee.feetype.EditFeeTypeService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditFeeTypeServiceImpl implements EditFeeTypeService {

    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private DataChangeDao dataChangeDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private RecruitTypeDao recruitTypeDao;
    @Autowired
    private LevelDao levelDao;

    @Override
    public void edit(FeeType feeType, long centerId, int isAudit, long operateId, String editReson) throws Exception {
        FeeType feeType2 = feeTypeDao.findByNameAndSchoolIdAndTypeIdAndLevelIdAndYearAndTerm(feeType.getName(), feeType.getSchoolId(),feeType.getTypeId(), feeType.getLevelId(), feeType.getYear(), feeType.getTerm());
        if (null != feeType2 && feeType2.getId() != feeType.getId()) {
            throw new BusinessException("费用类型已存在！");
        }
        //查询操作是否需要审核
        if(isAudit == User.ISOPERATEAUDIT_NOT) {
            if (null == feeType2) {
                feeType2 = feeTypeDao.findOne(feeType.getId());
            }
            feeType2.setName(feeType.getName());
            feeType2.setFee(feeType.getFee());
            feeType2.setSchoolId(feeType.getSchoolId());
            feeType2.setTypeId(feeType.getTypeId());
            feeType2.setLevelId(feeType.getLevelId());
            feeType2.setYear(feeType.getYear());
            feeType2.setTerm(feeType.getTerm());
            feeType2.setOperator(feeType.getOperator());
            feeType2.setOperateTime(DateUtil.getLongNowTime());
            feeTypeDao.save(feeType2);
        }else{
            String changeContent = "费用类型 ";
            String changeTableField = "";
            feeType2 = feeTypeDao.findOne(feeType.getId());
            School school = schoolDao.findOne(feeType2.getSchoolId());
            RecruitType recruitType = recruitTypeDao.findOne(feeType2.getTypeId());
            Level level = levelDao.findOne(feeType2.getLevelId());
            changeContent += school.getName()+recruitType.getName()+level.getName()+feeType2.getBatch()+"  ";

            if(!feeType.getName().equals(feeType2.getName())){
                changeContent += "费用类型<span style='color:red'>"+feeType2.getName()+"</span>变更为<span style='color:red'>"+feeType.getName()+"</span>  ";
                changeTableField += "name='"+feeType.getName()+"',";
            }
            if(feeType.getFee() != feeType2.getFee()){
                changeContent += "应交金额<span style='color:red'>"+feeType2.getFeeStr()+"</span>变更为<span style='color:red'>"+feeType.getFeeStr()+"</span>  ";
                changeTableField += "fee="+feeType.getFee()+",";
            }
            if(feeType.getSchoolId() != feeType2.getSchoolId()){
                School school2 = schoolDao.findOne(feeType.getSchoolId());
                changeContent += "高校<span style='color:red'>"+school.getName()+"</span>变更为<span style='color:red'>"+school2.getName()+"</span>  ";
                changeTableField += "school_id="+feeType.getSchoolId() +",";
            }
            if(feeType.getTypeId() != feeType2.getTypeId()){
                RecruitType recruitType2 = recruitTypeDao.findOne(feeType.getTypeId());
                changeContent += "招生类型<span style='color:red'>"+recruitType.getName()+"</span>变更为<span style='color:red'>"+recruitType2.getName()+"</span>  ";
                changeTableField += "type_id="+feeType.getTypeId() +",";
            }
            if(feeType.getLevelId() != feeType2.getLevelId()){
                Level level2 = levelDao.findOne(feeType.getLevelId());
                changeContent += "层次<span style='color:red'>"+level.getName()+"</span>变更为<span style='color:red'>"+level2.getName()+"</span>  ";
                changeTableField += "level_id="+feeType.getLevelId() +",";
            }
            if(feeType.getYear() != feeType2.getYear() || feeType.getTerm() != feeType2.getTerm()){
                changeContent += "批次<span style='color:red'>"+feeType2.getBatch()+"</span>变更为<span style='color:red'>"+feeType.getBatch()+"</span>  ";
                changeTableField += "year="+feeType.getYear() +",term="+feeType.getTerm()+",";
            }
            if(changeTableField.length() > 0){
                changeTableField = changeTableField.substring(0, changeTableField.length()-1);
            }

            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent(changeContent);
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_EDIT);
            dataChange.setChangeTable("fee_type");
            dataChange.setChangeTableId(feeType.getId());
            dataChange.setChangeTableField(changeTableField);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
