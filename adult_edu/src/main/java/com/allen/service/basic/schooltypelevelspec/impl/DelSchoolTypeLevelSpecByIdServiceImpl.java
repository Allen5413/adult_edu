package com.allen.service.basic.schooltypelevelspec.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.schooltypelevelspec.SchoolTypeLevelSpecDao;
import com.allen.dao.basic.schooltypelevelspeccourse.SchoolTypeLevelSpecCourseDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.entity.basic.*;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.user.User;
import com.allen.service.basic.schooltypelevelspec.DelSchoolTypeLevelSpecByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/10.
 */
@Service
public class DelSchoolTypeLevelSpecByIdServiceImpl implements DelSchoolTypeLevelSpecByIdService {

    @Autowired
    private SchoolTypeLevelSpecDao schoolTypeLevelSpecDao;
    @Autowired
    private SchoolTypeLevelSpecCourseDao schoolTypeLevelSpecCourseDao;
    @Autowired
    private DataChangeDao dataChangeDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private RecruitTypeDao recruitTypeDao;
    @Autowired
    private LevelDao levelDao;
    @Autowired
    private SpecDao specDao;

    @Override
    public void del(long id, long specId, long centerId, int isAudit, long operateId, String editReson) throws Exception {
        //查询下面是否有关联的课程，有的话就不能删除
        List<SchoolTypeLevelSpecCourse> list = schoolTypeLevelSpecCourseDao.findBySchoolTypeLevelSpecId(id);
        if(null != list && 0 < list.size()){
            throw new BusinessException("已经关联了课程信息，不能被删除");
        }
        //查询操作是否需要审核
        if(isAudit == User.ISOPERATEAUDIT_NOT) {
            schoolTypeLevelSpecDao.delete(id);
            specDao.delete(specId);
        }else{
            SchoolTypeLevelSpec schoolTypeLevelSpec = schoolTypeLevelSpecDao.findOne(id);
            School school = schoolDao.findOne(schoolTypeLevelSpec.getSchoolId());
            RecruitType recruitType = recruitTypeDao.findOne(schoolTypeLevelSpec.getRecruitTypeId());
            Level level = levelDao.findOne(schoolTypeLevelSpec.getLevelId());
            Spec spec = specDao.findOne(schoolTypeLevelSpec.getSpecId());

            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent("删除了"+school.getName()+recruitType.getName()+level.getName()+"下的<span style='color:red'>"+spec.getName()+"</span>专业");
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_DEL);
            dataChange.setChangeTable("school_type_level_spec");
            dataChange.setChangeTableId(id);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
