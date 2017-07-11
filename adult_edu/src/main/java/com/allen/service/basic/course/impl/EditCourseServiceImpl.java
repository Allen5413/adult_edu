package com.allen.service.basic.course.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.course.CourseDao;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.entity.basic.Course;
import com.allen.entity.basic.School;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.user.User;
import com.allen.service.basic.course.EditCourseService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class EditCourseServiceImpl implements EditCourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private DataChangeDao dataChangeDao;
    @Autowired
    private SchoolDao schoolDao;

    @Override
    public void edit(Course course, long centerId, int isOperateAudit, long operateId, String editReson) throws Exception {
        Course course2 = courseDao.findByCodeAndSchoolId(course.getCode(), course.getSchoolId());
        if(course2 != null && course2.getId() != course.getId() && course.getCode().equals(course2.getCode())){
            throw new BusinessException("课程编号已存在");
        }
        if(null == course2){
            course2 = courseDao.findOne(course.getId());
        }
        //查询操作是否需要审核
        if(isOperateAudit == User.ISOPERATEAUDIT_NOT) {
            course2.setCode(course.getCode());
            course2.setName(course.getName());
            courseDao.save(course2);
        }else {
            String changeContent = "";
            String changeField = "";
            if(!course2.getCode().equals(course.getCode())){
                changeContent += "课程编号为<span style='color:red'>"+course2.getCode()+"</span>变更为<span style='color:red'>"+course.getCode()+"</span>；";
                changeField += "code='"+course.getCode()+"',";
            }
            if(!course2.getName().equals(course.getName())){
                changeContent += "课程名称为<span style='color:red'>"+course2.getName()+"</span>变更为<span style='color:red'>"+course.getName()+"</span>；";
                changeField += "name='"+course.getName()+"',";
            }
            if(!StringUtil.isEmpty(changeField)){
                changeField = changeField.substring(0, changeField.length()-1);
                School school = schoolDao.findOne(course.getSchoolId());
                changeContent = school.getName()+"的"+changeContent;
            }
            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent(changeContent);
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_EDIT);
            dataChange.setChangeTable("course");
            dataChange.setChangeTableId(course.getId());
            dataChange.setChangeTableField(changeField);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
