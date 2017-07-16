package com.allen.service.eduadmin.recruittype.impl;

import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.eduadmin.teachplancourse.TeachPlanCourseDao;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.School;
import com.allen.entity.basic.Spec;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.user.User;
import com.allen.service.eduadmin.teachplan.DelTeachPlanByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/6/29.
 */
@Service
public class DelTeachPlanByIdServiceImpl implements DelTeachPlanByIdService {

    @Autowired
    private TeachPlanDao teachPlanDao;
    @Autowired
    private TeachPlanCourseDao teachPlanCourseDao;
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
    @Transactional
    public void del(long id, long centerId, int isAudit, long operateId, String editReson) throws Exception {
        //查询操作是否需要审核
        if(isAudit == User.ISOPERATEAUDIT_NOT) {
            teachPlanCourseDao.delByTeachPlanId(id);
            teachPlanDao.delete(id);
        }else{
            TeachPlan teachPlan = teachPlanDao.findOne(id);
            School school = schoolDao.findOne(teachPlan.getSchoolId());
            RecruitType recruitType = recruitTypeDao.findOne(teachPlan.getTypeId());
            Level level = levelDao.findOne(teachPlan.getLevelId());
            Spec spec = specDao.findOne(teachPlan.getSpecId());
            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent("删除了"+school.getName()+recruitType.getName()+level.getName()+spec.getName()+teachPlan.getYear()+"年"+(teachPlan.getTerm() == TeachPlan.TERM_SPRING ? "春季" : "秋季")+"的教学计划");
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_DEL);
            dataChange.setChangeTable("teach_plan");
            dataChange.setChangeTableId(id);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
