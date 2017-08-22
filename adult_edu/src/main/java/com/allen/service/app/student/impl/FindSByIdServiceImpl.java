package com.allen.service.app.student.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.eduadmin.studentcourse.FindStudentCourseDao;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.School;
import com.allen.entity.basic.Spec;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.user.User;
import com.allen.service.app.student.FindSByIdService;
import com.allen.service.fee.studentfee.FindStudentFeeByStudentIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/8/11.
 */
@Service
public class FindSByIdServiceImpl implements FindSByIdService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private LevelDao levelDao;
    @Autowired
    private SpecDao specDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private FindStudentFeeByStudentIdService findStudentFeeByStudentIdService;
    @Autowired
    private FindStudentCourseDao findStudentCourseDao;


    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入学生id");
        }
        Student student = studentDao.findOne(Long.parseLong(id));
        School school = schoolDao.findOne(student.getSchoolId());
        Level level = levelDao.findOne(student.getLevelId());
        Spec spec = specDao.findOne(student.getSpecId());
        User user = userDao.findOne(student.getUserId());

        //查询该学生的缴费详情集合
        JSONObject feeJSON = findStudentFeeByStudentIdService.find(student.getId());
        //查询该学生的课程信息
        Map<String, String> params = new HashMap<String, String>();
        params.put("centerId", student.getCenterId()+"");
        params.put("studentId", id);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(1);
        pageInfo.setCountOfCurrentPage(999);
        pageInfo = findStudentCourseDao.findPage(pageInfo, params);

        jsonObject.put("photoUrl", student.getPhotoUrl());
        jsonObject.put("name", student.getName());
        jsonObject.put("sex", student.getSex());
        jsonObject.put("phone", student.getPhone());
        jsonObject.put("idCard", student.getIdCard());
        jsonObject.put("address", student.getAddress());
        jsonObject.put("email", student.getEmail());
        jsonObject.put("qq", student.getQq());
        jsonObject.put("schoolName", school.getName());
        jsonObject.put("specName", spec.getName());
        jsonObject.put("levelName", level.getName());
        jsonObject.put("code", student.getCode());
        jsonObject.put("state", student.getState());
        jsonObject.put("code", student.getCode());
        jsonObject.put("userName", -1 == student.getUserId() ? "本部" : user.getName());
        jsonObject.put("signUpDate", student.getSignUpDate());
        jsonObject.put("feeState", student.getFeeState());
        jsonObject.put("fee", feeJSON);
        jsonObject.put("courseList", pageInfo.getPageResults());
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
